using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace InformationSystem.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class InformationSystemController : ControllerBase
    {
        [HttpPost]
        [Route("irrigation/start/record")]
        public string RegisterIrrigation()
        {
            //TODO: Code to execute the operation
            string result = "Register Irrigation Record executed";
            Console.WriteLine(DateTime.Now + " " + result);
            return result;
        }

        [HttpPost]
        [Route("tank/refill/record")]
        public string RegisterRefillRecord()
        {
            //TODO: Code to execute the operation
            string result = "Register Refill Record executed";
            Console.WriteLine(DateTime.Now + " " + result);
            return result;
        }





        [HttpGet]
        [Route("operations")]
        public string GetOperations()
        {

            List<Operation> operations = new List<Operation>();

            operations.Add(new Operation
                {
                    id = "irrigationstartrecord",
                    name = "Register Irrigation Record",
                    path = "irrigation/start/record",
                    method = "POST"
                }
            );

            operations.Add(new Operation
                {
                    id = "tankrefillrecord",
                    name = "Register Refill Record",
                    path = "tank/refill/record",
                    method = "POST"
                }
            );

            return JsonSerializer.Serialize(operations);
        }
    }
}

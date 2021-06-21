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
        [Route("patient/fall")]
        public string RegisterFall()
        {
            //TODO: Code to execute the operation
            string result = "Register Fall executed";
            Console.WriteLine(DateTime.Now + " " + result);
            return result;
        }

        [HttpPost]
        [Route("health/parameters")]
        public string RegisterHealthParameters()
        {
            //TODO: Code to execute the operation
            string result = "Register Health Parameters executed";
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
                    id = "registerfall",
                    name = "Register Fall",
                    path = "patient/fall",
                    method = "POST"
                }
            );

            operations.Add(new Operation
                {
                    id = "registerhealthparameters",
                    name = "Register Health Parameters",
                    path = "health/parameters",
                    method = "POST"
                }
            );

            return JsonSerializer.Serialize(operations);
        }
    }
}

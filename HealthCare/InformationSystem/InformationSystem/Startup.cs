using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace InformationSystem
{
    public class Startup
    {
        private string ID = "InformationSystem";

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            this.RegisterToEureka();
           
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void RegisterToEureka()
        {

            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:2222/eureka/apps/" + ID);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {

                string json = "{" +
                   "\"instance\" : {" +
                       "\"instanceId\": \"localhost:" + ID + "2\"," +
                       "\"hostName\": \"localhost\"," +
                       "\"app\": \"" + ID + "\"," +
                       "\"ipAddr\": \"127.0.0.1\"," +
                       "\"vipAddress\": \"localhost\"," +
                       "\"secureVipAddress\": \"localhost\"," +
                       "\"status\": \"UP\"," +
                       "\"port\": { \"$\" : \"5001\", \"@enabled\": \"true\"}," +
                       "\"healthCheckUrl\": \"http://localhost:5001/health\"," +
                       "\"statusPageUrl\": \"http://localhost:5001/status\"," +
                       "\"homePageUrl\": \"http://localhost:5001\"," +
                       "\"dataCenterInfo\": { " +
                           "\"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\", " +
                           "\"name\": \"MyOwn\"" +
                       "}" +
                  "}" +
               "}";

                streamWriter.Write(json);
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                var result = streamReader.ReadToEnd();
            }

            Console.WriteLine("IoT Device Microservice Registered to Eureka");
        }
    }
}

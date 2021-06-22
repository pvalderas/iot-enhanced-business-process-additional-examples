using System;
using System.Collections.Generic;
using System.Text.Json;
using RabbitMQ.Client;

namespace SoilMoistureSensor
{
    class Program
    {
		private static String queue = "context";

		private static String deviceType = "SoilMostureSensor";
		private static String deviceId = "soilmosture438";
		private static String property = "Soil Mosture";
		private static String feature = "Soil";


		static void Main(string[] args)
		{
			float moisture = GetManualMoisture(); // getMoistureFromSensor() in a real environment

			ContextProperty contextProperty = createContextProperty(moisture);

			if (AskToPublishManually(contextProperty)) SendToEventBus(contextProperty); // Asking is not needed in a real environment

		}

		private static ContextProperty createContextProperty(float moisture)
		{
			ContextProperty contextProperty = new ContextProperty
			{
				device = deviceType,
				id = deviceId,
				property = property,
				feature = feature,
				dataProps = new List<Property>{
					new Property{
						name="moisture",
						value= moisture
					},
					new Property{
						name="timestamp",
						value=DateTime.Now
					}
				}
			};

			return contextProperty;
		}

		private static float GetManualMoisture()
		{
			Console.WriteLine("************************************");
			Console.WriteLine("***********MOISTURE SENSOR*******");
			Console.WriteLine("************************************");
			Console.WriteLine("Introduce moisture level: ");

			String moisture = Console.ReadLine();

			return float.Parse(moisture);
		}

		private static Boolean AskToPublishManually(ContextProperty contextProperty)
		{
			Console.WriteLine("**************************************");
			Console.WriteLine(JsonSerializer.Serialize(contextProperty));
			Console.WriteLine("**************************************");
			Console.WriteLine("Publish (Y/N)?");
			ConsoleKeyInfo key = Console.ReadKey();
			if (key.Key == ConsoleKey.Y)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		private static float getTempFromSensor()
		{
			float value = 0;

			// TODO Code to obtain data from the physcial device 

			return value;
		}

		private static void SendToEventBus(ContextProperty contextProperty)
		{

			ConnectionFactory factory = new ConnectionFactory();
			factory.HostName = "localhost";
			IConnection conn = factory.CreateConnection();

			IModel channel = conn.CreateModel();

			channel.QueueDeclare(queue, false, false, false, null);

			byte[] messageBodyBytes = System.Text.Encoding.UTF8.GetBytes(JsonSerializer.Serialize(contextProperty));
			channel.BasicPublish("", queue, null, messageBodyBytes);

			channel.Close();
			conn.Close();

		}
	}
}

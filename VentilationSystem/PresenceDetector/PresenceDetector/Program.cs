using System;
using System.Collections.Generic;
using System.Text.Json;
using RabbitMQ.Client;

namespace PresenceDetector
{
    class Program
    {
		private static String queue = "context";

		private static String deviceType = "PresenceDetector";
		private static String deviceId = "presencedetector01";
		private static String property = "Presence of a Person";
		private static String feature = "Room";


		static void Main(string[] args)
		{
			Boolean status = GetManualStatus(); // getStatusFromSensor() in a real environment

			ContextProperty contextProperty = createContextProperty(status);

			if (AskToPublishManually(contextProperty)) SendToEventBus(contextProperty); // Asking is not needed in a real environment

		}

		private static ContextProperty createContextProperty(Boolean status)
		{
			ContextProperty contextProperty = new ContextProperty
			{
				device = deviceType,
				id = deviceId,
				property = property,
				feature = feature,
				dataProps = new List<Property>{
					new Property{
						name="status",
						value= status
					},
					new Property{
						name="timestamp",
						value=DateTime.Now
					}
				}
			};

			return contextProperty;
		}

		private static Boolean GetManualStatus()
		{
			Console.WriteLine("************************************");
			Console.WriteLine("***********PRESENCE DETECTOR*******");
			Console.WriteLine("************************************");

			Console.WriteLine("Introduce detector status (True/False): ");

			String status = Console.ReadLine();
			status=status.ToLower();
			while (!status.Equals("true") && !status.Equals("false") && !status.Equals("t") && !status.Equals("f"))
			{
				Console.WriteLine("Introduce detector status (True/False): ");
				status = Console.ReadLine();
				status = status.ToLower();
			}

			if (status.Equals("t") || status.Equals("true")) return true;
			return false;
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

		private static Boolean getStatusFromSensor()
		{
			Boolean value = false;

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

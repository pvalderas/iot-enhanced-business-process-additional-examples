package es.upv.pros.pvalderas.tanklevelsensor;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TankLevelSensor {
	
	private static final String queue="context";
	
	private static final String device = "TankLevelSensor";
	private static final String id = "tanklevelsensor123";
	private static final String property = "Tank Water Level";
	private static final String feature = "Tank";
	
	
	private static Scanner keyboard= new Scanner(System.in);
	
	
	public static void main(String[] args) throws JSONException, IOException, TimeoutException{	
		
		Float level=getManualWaterLevel(); // getWaterLevelFromSensor() in a real environment
		
		JSONObject contextProperty=createContextProperty(level);
		
		if(askToPublishManually(contextProperty)) sendToEventBus(contextProperty); // Asking is not needed in a real environment
		
	}
	
	private static JSONObject createContextProperty(Float level) throws JSONException{
		JSONObject contextProperty=new JSONObject();
		contextProperty.put("device", device);
		contextProperty.put("id", id);
		contextProperty.put("property", property);
		contextProperty.put("feature", feature);
			
		JSONArray properties=new JSONArray();
		
		JSONObject levelJSON=new JSONObject();
		levelJSON.put("name", "level");
		levelJSON.put("value", level);
			
		JSONObject timeJSON=new JSONObject();
		timeJSON.put("name", "timeStamp");
		timeJSON.put("value", new Timestamp(System.currentTimeMillis()));
		
		properties.put(levelJSON);
		properties.put(timeJSON);
				
		contextProperty.put("properties", properties);
		
		return contextProperty;
	}
	
	private static Float getManualWaterLevel(){
		
		System.out.println("***********************************");
		System.out.println("*********TANK LEVEL SENSOR*********");
		System.out.println("***********************************");
		
		System.out.print("Introduce a water level: ");
		Float level=Float.parseFloat(keyboard.nextLine());
		
		return level;
	}
	
	private static boolean askToPublishManually(JSONObject contextProperty){
		System.out.println("**************************************");
		System.out.println(contextProperty.toString());
		System.out.println("**************************************");
		System.out.print("Publish (Y/N)?");
		char key=keyboard.next().charAt(0);
		keyboard.close();
		if(key=='Y' || key=='y'){
			return true;
		}
		return false;
	}
	
	private static Float getWaterLevelFromSensor(){
		Float level=null;
		// TODO Code to obtain data from the physcial device 
		return level;
    }

	private static void sendToEventBus(JSONObject contextProperty) throws IOException, TimeoutException{
	
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(queue, false, false, false, null);

		channel.basicPublish("", queue, null, contextProperty.toString().getBytes());
		
		channel.close();
		connection.close();
		

	}
	
	
}

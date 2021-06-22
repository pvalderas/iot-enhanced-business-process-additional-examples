package es.upv.pros.pvalderas.accelerometer;

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

public class Accelerometer {
	
	private static final String queue="context";
	
	private static final String device = "Accelerometer";
	private static final String id = "patient123Acc";
	private static final String property = "Patient Fall";
	private static final String feature = "Patient";
	
	
	private static Scanner keyboard= new Scanner(System.in);
	
	
	public static void main(String[] args) throws JSONException, IOException, TimeoutException{	
		
		Float[] values=getManualValues(); // getValuesFromSensor() in a real environment
		
		JSONObject contextProperty=createContextProperty(values);
		
		if(askToPublishManually(contextProperty)) sendToEventBus(contextProperty); // Asking is not needed in a real environment
		
	}
	
	private static JSONObject createContextProperty(Float[] values) throws JSONException{
		JSONObject contextProperty=new JSONObject();
		contextProperty.put("device", device);
		contextProperty.put("id", id);
		contextProperty.put("property", property);
		contextProperty.put("feature", feature);
			
		JSONArray properties=new JSONArray();
		
		JSONObject x=new JSONObject();
		x.put("name", "x");
		x.put("value", values[0]);
		
		JSONObject y=new JSONObject();
		y.put("name", "y");
		y.put("value", values[1]);
		
		JSONObject z=new JSONObject();
		z.put("name", "z");
		z.put("value", values[2]);
		
		JSONObject timeJSON=new JSONObject();
		timeJSON.put("name", "timeStamp");
		timeJSON.put("value", new Timestamp(System.currentTimeMillis()));
		
		properties.put(x);
		properties.put(y);
		properties.put(z);
		properties.put(timeJSON);
				
		contextProperty.put("properties", properties);
		
		return contextProperty;
	}
	
	private static Float[] getManualValues(){
		Float[] values=new Float[3];
		
		System.out.println("***********************************");
		System.out.println("***********ACCELEROMETER***********");
		System.out.println("***********************************");
		
		System.out.print("Introduce X Value: ");
		values[0]=Float.parseFloat(keyboard.nextLine());
		
		System.out.print("Introduce Y Value: ");
		values[1]=Float.parseFloat(keyboard.nextLine());
		
		System.out.print("Introduce Z Value: ");
		values[2]=Float.parseFloat(keyboard.nextLine());
		
		return values;
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
	
	private static Float[] getValuesFromSensor(){
		Float[] values=null;
		// TODO Code to obtain data from the physcial device 
		return values;
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

package es.upv.pros.pvalderas.notificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import es.upv.pros.pvalderas.microservice.iotdevice.IoTDevice;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"es.upv.pros.pvalderas.notificationsystem","es.upv.pros.pvalderas.microservice.iotdevice"})
@IoTDevice(serviceAPIClass=NotificationSystemHTTPController.class)
public class NotificationSystem {
	
	public static void main(String[] args) {
		SpringApplication.run(NotificationSystem.class, args);
	}
}

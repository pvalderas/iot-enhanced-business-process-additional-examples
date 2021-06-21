package es.upv.pros.pvalderas.ventilationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import es.upv.pros.pvalderas.microservice.iotdevice.IoTDevice;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"es.upv.pros.pvalderas.ventilationsystem","es.upv.pros.pvalderas.microservice.iotdevice"})
@IoTDevice(serviceAPIClass=VentilationSystemHTTPController.class)
public class VentilationSystem {
	
	public static void main(String[] args) {
		SpringApplication.run(VentilationSystem.class, args);
	}
}

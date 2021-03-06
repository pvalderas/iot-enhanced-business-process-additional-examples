package es.upv.pros.pvalderas.tankvalve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import es.upv.pros.pvalderas.microservice.iotdevice.IoTDevice;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"es.upv.pros.pvalderas.tankvalve","es.upv.pros.pvalderas.microservice.iotdevice"})
@IoTDevice(serviceAPIClass=TankValveHTTPController.class)
public class TankValve {
	
	public static void main(String[] args) {
		SpringApplication.run(TankValve.class, args);
	}
}

package es.upv.pros.pvalderas.informationsystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.upv.pros.pvalderas.microservice.iotdevice.BPMNOperation;

public class InformationSystemHTTPController {
	
	@RequestMapping(
			  value = "/room/state/busy", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Register Room as Busy", id = "registerroombusy")
	 public String busy() {
		//TODO: Code to execute the operation
		return "Register Room as Busy executed";
		
	}
	
	@RequestMapping(
			  value = "/room/state/free",  
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Register Room as Free", id = "registerroomfree")
	 public String free() {
		//TODO: Code to execute the operation
		return "Register Room as Free executed";
		
	}

}

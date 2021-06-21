package es.upv.pros.pvalderas.ventilationsystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.upv.pros.pvalderas.microservice.iotdevice.BPMNOperation;

public class VentilationSystemHTTPController {
	
	@RequestMapping(
			  value = "/ventilation/system", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Activate Ventilation System", id = "activateventilationsystem")
	 public String activate() {
		//TODO: Code to execute the operation
		return "Activate Ventilation System executed";
		
	}
	
	@RequestMapping(
			  value = "/ventilation/system", 
			  method = RequestMethod.DELETE)
	@BPMNOperation(name = "Stop Ventilation System", id = "activateventilationsystem")
	 public String stop() {
		//TODO: Code to execute the operation
		return "Activate Ventilation System executed";
		
	}
	
	@RequestMapping(
			  value = "/ventilation/system/increase", 
			  method = RequestMethod.PUT)
	@BPMNOperation(name = "Increase Ventilation", id = "increaseventilation")
	 public String increase() {
		//TODO: Code to execute the operation
		return "Increase Ventilation System executed";
		
	}
	
	@RequestMapping(
			  value = "/ventilation/system/decrease", 
			  method = RequestMethod.PUT)
	@BPMNOperation(name = "Decrease Ventilation", id = "decreaseventilation")
	 public String decrease() {
		//TODO: Code to execute the operation
		return "Decrease Ventilation System executed";
		
	}

}

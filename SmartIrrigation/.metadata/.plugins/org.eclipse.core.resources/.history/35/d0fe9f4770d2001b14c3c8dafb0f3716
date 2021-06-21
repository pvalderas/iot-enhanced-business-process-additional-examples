package es.upv.pros.pvalderas.alarm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.upv.pros.pvalderas.microservice.iotdevice.BPMNOperation;

public class AlarmHTTPController {
	
	@RequestMapping(
			  value = "/alarm", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Activate", id = "activate")
	 public String activate() {
		return "Activate Alarm executed";
		
	}
	
	@RequestMapping(
			  value = "/alarm", 
			  method = RequestMethod.DELETE)
	@BPMNOperation(name = "Deactivate", id = "deactivate")
	 public String dectivate() {
		return "Deactivate Alarm executed";
		
	}

}

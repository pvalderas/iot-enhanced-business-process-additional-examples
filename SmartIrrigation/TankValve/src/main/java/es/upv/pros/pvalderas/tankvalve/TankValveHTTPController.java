package es.upv.pros.pvalderas.tankvalve;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.upv.pros.pvalderas.microservice.iotdevice.BPMNOperation;

public class TankValveHTTPController {
	
	@RequestMapping(
			  value = "/tank/valve", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Open Tank Valve", id = "opentankvale")
	 public String openValvee() {
		//TODO: Code to execute the operation
		return "Open Tank Valve executed";
		
	}
	
	@RequestMapping(
			  value = "/tank/valve", 
			  method = RequestMethod.DELETE)
	@BPMNOperation(name = "Close Tank Valve", id = "closetankvalve")
	 public String closeValve() {
		//TODO: Code to execute the operation
		return "Close Tank Valve executed";
		
	}

}

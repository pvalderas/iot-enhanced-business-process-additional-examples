package es.upv.pros.pvalderas.notificationsystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.upv.pros.pvalderas.microservice.iotdevice.BPMNOperation;

public class NotificationSystemHTTPController {
	
	@RequestMapping(
			  value = "/notification/caregiver", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Notify a Caregiver", id = "notifycaregiver")
	 public String notifycaregiver() {
		//TODO: Code to execute the operation
		return "Notify a Caregiver executed";
		
	}
	
	@RequestMapping(
			  value = "/notification/nurse", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Notify a Nurse", id = "notifynurse")
	 public String notifynurse() {
		//TODO: Code to execute the operation
		return "Notify a Nurse executed";
		
	}
	
	@RequestMapping(
			  value = "/notification/doctorr", 
			  method = RequestMethod.POST)
	@BPMNOperation(name = "Notify a Doctor", id = "notifydoctor")
	 public String notifydoctor() {
		//TODO: Code to execute the operation
		return "Notify a Doctor executed";
		
	}
		
}

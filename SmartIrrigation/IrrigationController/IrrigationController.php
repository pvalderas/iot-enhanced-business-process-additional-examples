<?php

class IrrigationController{

	public function start(){
		return "Start irrigation executed";
	}
	
	public function stop(){
		return "Stop irrigation executed";
	}

	public function operations(){
		$operations=array();

		$start=array();
		$start['id']="startirrigation";
		$start['name']="Start Irrigation";
		$start['path']="/";
		$start['method']="POST";

		$stop=array();
		$stop['id']="stopirrigation";
		$stop['name']="Stop Irrigation";
		$stop['path']="/";
		$stop['method']="DELETE";

		$operations[]=$start;
		$operations[]=$stop;

		return json_encode($operations);

	}
}

?>
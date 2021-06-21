<?php

class CameraController{

	public function turnOn(){
		// TODO: Code to execute the operation
		return "Turn On executed";
	}
	
	public function turnOff(){
		// TODO: Code to execute the operation
		return "Turn Off executed";
	}

	public function operations(){
		$operations=array();

		$on=array();
		$on['id']="turnon";
		$on['name']="Turn On";
		$on['path']="/";
		$on['method']="POST";

		$off=array();
		$off['id']="turnoff";
		$off['name']="Turn Off";
		$off['path']="/";
		$off['method']="DELETE";

		$operations[]=$on;
		$operations[]=$off;

		return json_encode($operations);

	}
}

?>
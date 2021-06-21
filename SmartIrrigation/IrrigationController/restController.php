<?php

	include 'IrrigationController.php';
	include 'EurekaManager.php';


	$path=$_SERVER['REQUEST_URI'];
	$microserviceID="IrrigationController";
	$irrigationController=new IrrigationController();

	$resources = preg_split("/\//", substr($path,1));
	//$resources[0] = irrigationcontroller
	
	switch($resources[1]){
		case "operations":if($_SERVER['REQUEST_METHOD']=='GET') echo $irrigationController->operations();break;
		case "eureka": 	if($_SERVER['REQUEST_METHOD']=='PUT'){
							$eurekaManager=new EurekaManager($microserviceID);
							$eurekaManager->register();
						}
						break;
		default:	
					switch($_SERVER['REQUEST_METHOD']){
						case 'POST': echo $irrigationController->start();
									break;
						case 'DELETE':echo $irrigationController->stop();
									break;
					}
	}

?>
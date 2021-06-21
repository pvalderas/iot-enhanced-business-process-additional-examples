<?php

	include 'CameraController.php';
	include 'EurekaManager.php';


	$path=$_SERVER['REQUEST_URI'];
	$microserviceID="Camera";
	$cameraController=new CameraController();

	$resources = preg_split("/\//", substr($path,1));
	//$resources[0] = camera
	
	switch($resources[1]){
		case "operations":if($_SERVER['REQUEST_METHOD']=='GET') echo $cameraController->operations();break;
		case "eureka": 	if($_SERVER['REQUEST_METHOD']=='PUT'){
							$eurekaManager=new EurekaManager($microserviceID);
							$eurekaManager->register();
						}
						break;
		default:	
					switch($_SERVER['REQUEST_METHOD']){
						case 'POST': echo $cameraController->on();
									break;
						case 'DELETE':echo $cameraController->off();
									break;
					}
	}

?>
<?php
	include 'EurekaManager.php';

	$microserviceID="IrrigationController";

	$eurekaManager=new EurekaManager($microserviceID);
	echo $eurekaManager->register();
?>
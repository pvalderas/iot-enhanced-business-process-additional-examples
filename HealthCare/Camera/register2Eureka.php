<?php
	include 'EurekaManager.php';

	$microserviceID="Camera";

	$eurekaManager=new EurekaManager($microserviceID);
	echo $eurekaManager->register();
?>
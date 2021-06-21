<?php

class EurekaManager{

	private $microserviceID;

	public function __construct(string $microserviceID) {
        $this->microserviceID = $microserviceID;
    }

    public function register(){
        require_once 'config.php';

		$url=$eurekaURL.$this->microserviceID;
		$payload=$this->getPayload();
        
		$ch = curl_init($url);
		
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));


		$data = curl_exec($ch);
		curl_close($ch);

        return $data;
    }

    private function getPayload(){
    	$json=array();
    	$instance=array();
    	$instance['instanceId']="localhost:$this->microserviceID";
    	$instance['hostName']="localhost";
    	$instance['app']=$this->microserviceID;
    	$instance['ipAddr']="127.0.0.1";
    	$instance['vipAddress']="localhost";
    	$instance['secureVipAddress']="localhost";
    	$instance['status']="UP";
    	$port=array();
    	$port['$']="80";
    	$port['@enabled']="true";
    	$instance['port']=$port;
    	$instance['healthCheckUrl']="http://localhost:80/health";
    	$instance['statusPageUrl']="http://localhost:80/status";
    	$instance['homePageUrl']="http://localhost:80";
    	$dataCenterInfo=array();
    	$dataCenterInfo['@class']="com.netflix.appinfo.InstanceInfo\$DefaultDataCenterInfo";
    	$dataCenterInfo['name']="MyOwn";
    	$instance['dataCenterInfo']=$dataCenterInfo;
    	$json['instance']=$instance;

    	return json_encode($json);
    }

}


?>
import requests

class EurekaManager:
	eurekaURL="http://localhost:2222/eureka/apps/"

	def __init__(self, system):
		self.system = system

	def register(self):
		url=self.eurekaURL+self.system

		data ={
 				"instance": {
			    "instanceId": self.system,
			    "hostName": "localhost",
			    "app": self.system,
			    "ipAddr": "127.0.0.1",
			    "vipAddress": "localhost",
			    "secureVipAddress": "localhost",
			    "status": "UP",
			    "port": {"$": "8000","@enabled": "true"},
			    "healthCheckUrl": "http://localhost:8000/health",
			    "statusPageUrl": "http://localhost:8000/status",
			    "homePageUrl": "http://localhost:8000",
			    "dataCenterInfo": {
			      "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
			      "name": "MyOwn"
			    }
			  }
			}

		response = requests.post(url, json = data)
		return response
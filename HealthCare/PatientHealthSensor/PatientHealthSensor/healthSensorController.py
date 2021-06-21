import json

import falcon


class HealthSensorController:

    def on_get(self, req, resp):
    	//TODO: Code to execute the operation
        resp.text = "Sense Health Parameters executed";


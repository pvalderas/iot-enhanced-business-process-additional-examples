import json

import falcon


class SensorController:

    def on_get(self, req, resp, room):
        resp.text = "Read C02 level in " + room + " executed";


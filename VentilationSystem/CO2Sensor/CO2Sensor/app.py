import falcon

from .sensorController import SensorController
from .eurekaManager import EurekaManager


app = application = falcon.App()

eurekaManager = EurekaManager("CO2Sensor")
eurekaManager.register()

controller = SensorController()
app.add_route('/controller/{room}', controller)
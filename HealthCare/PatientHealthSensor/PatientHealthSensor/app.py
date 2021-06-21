import falcon

from .healthSensorController import HealthSensorController
from .eurekaManager import EurekaManager


app = application = falcon.App()

eurekaManager = EurekaManager("PatientHealthSensor")
eurekaManager.register()

controller = SensorController()
app.add_route('/patient/health/sensor', controller)
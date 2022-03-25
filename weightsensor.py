
import time
from gpiozero import *

weight_sensor = MCP3008(1)


offset = 779.1 +16.75
def test():
    while True:
        raw = weight_sensor.raw_value

        print(raw)
        print(weight_sensor.voltage)
        print("The weight of the bird is " +  str(abs(round(8.3774*raw - offset, 2))) + " gram")
        time.sleep(1)


# test()

def weigh_cycle():
    raw = abs(round(8.3774*weight_sensor.raw_value - offset, 2))
    time.sleep(0.1)
    raw += abs(round(8.3774*weight_sensor.raw_value - offset, 2))
    time.sleep((0.1))
    raw += abs(round(8.3774*weight_sensor.raw_value - offset, 2))
    raw /= 3
    return raw




























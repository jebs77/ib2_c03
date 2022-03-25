from gpiozero import *
import time

treshold_voltage = 3
def get_LDR():
    sensor = MCP3008(0)
    while True:
        if sensor.voltage <= treshold_voltage:
            return 0 #dark
        elif sensor.voltage>treshold_voltage:
            return 1 #light


def ldrtest():
    sensor = MCP3008(0)
    while True:

        if sensor.voltage <= treshold_voltage:
            print(sensor.voltage)
            print("dark")
        elif sensor.voltage>treshold_voltage:
            print(sensor.voltage)
            print("light")
        time.sleep(1)

# ldrtest()
from gpiozero import *

def ldrtest():
    sensor = MCP3008(0)
    while True:
        if sensor.voltage <= 0.5:
            return 0 #dark
        elif sensor.voltage>0.5:
            return 1 #light



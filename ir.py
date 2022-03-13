#code voor de IR sensor
#receiver: 22
#emitter: 12


import time
import gpiozero
from gpiozero import *
from signal import *

LED_green = LED("BOARD11")
LED_orange = LED("BOARD13")
LED_red = LED("BOARD15")
emitter = LED("BOARD12")

receiver_1 = gpiozero.InputDevice("BOARD22")
receiver_2 = gpiozero.InputDevice("BOARD36")


def turn_off_LEDs():
    LED_red.off()
    LED_orange.off()
    LED_green.off()


def check_food_status():
    # while True:
        emitter.on()
        time.sleep(1)
        turn_off_LEDs()
        state = 0
        print("LED ON", receiver_1.value)
        if receiver_1.value == 0:
            print("More than 66% of food left")
            state = 1
            LED_green.on()
        elif receiver_1.value == 1 and receiver_2.value == 0:
            print("Between 66% and 33 % of food left")
            LED_orange.on()
            state = 2
        else:
            print("Less than 33% of food left")
            LED_red.on()
            state = 3
        emitter.off()
        return state        #commenten bij testen van de ir sensor







# def test_led():
#     while True:
#         LED_green.on()
#         time.sleep(1)
#         LED_green.off()
#         LED_orange.on()
#         time.sleep(1)
#         LED_orange.off()
#         LED_red.on()
#         time.sleep(1)
#         LED_red.off()
# test_led()

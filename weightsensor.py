
import time
from gpiozero import *

weight_sensor = MCP3008(1)

def test():
    while True:
        raw = weight_sensor.raw_value

        print(raw)
        print(weight_sensor.voltage)
        print("The weight of the bird is " +  str(abs(round(8.3774*raw - 779.1, 2))) + " gram")
        time.sleep(1)


test()




























# def get_birds_in_rage(min, max):
#     allowed_birds = []
#     for i in all_birds:
#         if i[2] > min and i[1] < max:
#             allowed_birds.append(i)
#     return allowed_birds



# def weigh_cycle(min, max):
#     allowed_birds = get_birds_in_rage(min, max)
#
#     bird_measured = False
#     treshold = 5        # minimum value, added becaus the idle read value of the weight sensor will probably be higher
#     # than 0 should be adjusted when measurements are made
#     while not bird_measured:
#         measured_value = weight_sensor.value
#         # measured_value = 500
#         # measured_value = 80
#
#         if min < measured_value < max:
#             possible_birds = get_possible_birds(measured_value, allowed_birds)
#             bird_measured = True
#         elif measured_value < treshold:
#             time.sleep(5);
#             bird_measured = False
#         else:
#             close_hatch()
#             possible_birds = get_possible_birds(measured_value, all_birds)
#             bird_measured = True
#     return possible_birds
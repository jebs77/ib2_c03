import time

import requests

import buzzer
import motor
import LDR
import weightsensor
import ir
import random

def test_db():
    response = requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
    element = response.json()[0]['component_on']

    print(element)
test_db()


def test_put():
    thingy = "https://studev.groept.be/api/a21ib2c03/testPythonPut/"
    print(thingy)
    requests.get(thingy+str(8))

# test_put()



def main():
    index = 0
    while True:
        # check whether there is light outside, if so, we can check the other sensors
        light_status = LDR.get_LDR_value()
        requests.get("https://studev.groept.be/api/a21ib2c03/updateLDR/"+str(light_status))
        if light_status == 1:
            if index ==5:
                #update_ir
                food_val = ir.check_food_status()
                requests.get("https://studev.groept.be/api/a21ib2c03/updateIR/"+str(food_val))

            #update weight_sensor
            weight = weightsensor.get_value()
            requests.get("https://studev.groept.be/api/a21ib2c03/updateWeightsensor/"+ str(weight))
            requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
            # minVal =
            # maxVal =
            allowed = weightsensor.allowed_birds(weight, 100, 200)
            if allowed == 1:
                last_motor_state_json =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
                last_motor_state = last_motor_state_json.json()[0]['component_on']
                #select motor state
                if last_motor_state ==0:
                    motor.open_hatch()
                    requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(last_motor_state))
            else:

                buzzer_on = 1 #check buzzer state via querry (to do)
                if buzzer_on == 1:
                    buzzer.turn_on()

                last_motor_state = 1 #check buzzer state via querry (to do)
                if last_motor_state ==1:
                    motor.close_hatch()
                    requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(0))

            index +=1
            time.sleep(1)
            if buzzer_on == 1:
                buzzer.turn_off()
        else:
            time.sleep(5)
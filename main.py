import time

import requests
import json
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
    last_motor_state_json =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
    last_motor_state = last_motor_state_json.json()[0]['component_on']
    print(last_motor_state)

test_db()


def test_put():
    read = "https://studev.groept.be/api/a21ib2c03/testPythonPut/"
    print(read)
    requests.get(read+str(12))

# test_put()



def main():
    index = 0
    allowed = 0
    while True:
        # check whether there is light outside, if so, we can check the other sensors
        light_status = LDR.get_LDR()
        # light_status = 1
        requests.get("https://studev.groept.be/api/a21ib2c03/updateLDR/"+str(light_status))
        print("adjusting light status to " + str(light_status))
        if light_status == 1:
            print("index" + str(index))
            if index ==5:
                #update_ir
                food_val = ir.check_food_status()
                print(food_val)
                requests.get("https://studev.groept.be/api/a21ib2c03/updateIR/"+str(food_val))
                index = 0

            #update weight_sensor
            weight = weightsensor.weigh_cycle()
            print("weight = "+ str(weight))
            requests.get("https://studev.groept.be/api/a21ib2c03/updateWeightsensor/"+ str(weight))
            requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")

            #check allowed bird
            response = requests.get("https://studev.groept.be/api/a21ib2c03/getAllIds")
            all_ids = response.text
            if "1" in all_ids:
                if (weight >9) and (weight <= 25):
                    allowed = 1
            elif "2" in all_ids:
                if (weight > 25) and (weight <= 100):
                    allowed = 1
            elif "3" in all_ids:
                if (weight> 100) and (weight <= 200):
                    allowed = 1
            elif "4" in all_ids:
                if (weight >200):
                    allowed =0
            else:
                allowed =0


            print("allowed = " + str(allowed))
            if allowed == 1:
                last_motor_state_json =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
                last_motor_state = last_motor_state_json.json()[0]['component_on']
                #select motor state
                if last_motor_state ==0:
                    motor.close()
                    requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(last_motor_state))
            else:
                response = requests.get("https://studev.groept.be/api/a21ib2c03/GetBuzzerInfo")
                buzzerState = response.json()[0]["manual"]
                if buzzerState == 1:
                    buzzer.buzzer()

                last_motor_state = 1 #check buzzer state via querry (to do)
                if last_motor_state ==1:
                    motor.open()
                    requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(0))

            allowed = 0
            index +=1
            time.sleep(1)
            if buzzerState == 1:
                buzzer.turn_off()
        else:
            time.sleep(1)

main()
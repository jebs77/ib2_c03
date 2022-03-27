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
        # light_status = LDR.get_LDR()
        light_status = 1 #for testing
        requests.get("https://studev.groept.be/api/a21ib2c03/updateLDR/"+str(light_status))
        print("adjusting light status to " + str(light_status))
        if light_status == 1:
            print("index" + str(index))
            if index ==5:
                #update_ir
                food_val = ir.check_food_status()
                food_val = 2
                print(food_val)
                requests.get("https://studev.groept.be/api/a21ib2c03/updateIR/"+str(food_val))
                index = 0

            #update weight_sensor
            weight = weightsensor.weigh_cycle()
            print("weight = "+ str(weight))
            requests.get("https://studev.groept.be/api/a21ib2c03/updateWeightsensor/"+ str(weight))

            #check allowed bird
            response = requests.get("https://studev.groept.be/api/a21ib2c03/getAllIds")
            all_ids = response.text
            if "1" in all_ids:
                if (weight >9) and (weight <= 25):
                    allowed = 1
            if "2" in all_ids:
                if (weight > 25) and (weight <= 100):
                    allowed = 1
            if "3" in all_ids:
                if (weight> 100) and (weight <= 200):
                    allowed = 1
            if "4" in all_ids:
                if (weight >200):
                    allowed =1
            else:
                allowed =0


            print("allowed = " + str(allowed))
            response = requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
            manual_hatch = int(response.json()[0]["manual"])
            print("manual" + str(manual_hatch))

            if manual_hatch == 0:
                if allowed == 1:
                    last_motor_state_response =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
                    last_motor_state = last_motor_state_response.json()[0]['current_measured']
                    #select motor state
                    if last_motor_state ==str(0):
                        motor.open()
                        print("opening hatch")
                        requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(1))
                else:
                    last_motor_state_response =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
                    last_motor_state = last_motor_state_response.json()[0]['current_measured']
                    if last_motor_state ==str(1):
                        motor.close()
                        print("closing hatch")
                        requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(0))
                    response = requests.get("https://studev.groept.be/api/a21ib2c03/GetBuzzerInfo")
                    buzzerState = response.json()[0]["manual"]
                    if buzzerState == str(1):
                        buzzer.buzzer()
            else:
                last_motor_state_response =  requests.get("https://studev.groept.be/api/a21ib2c03/getHatchInfo")
                last_motor_state = last_motor_state_response.json()[0]['current_measured']
                if last_motor_state == 1:
                    motor.close()
                    print("closing hatch")
                    requests.get("https://studev.groept.be/api/a21ib2c03/updateMotor/"+str(0))
            index +=1
            time.sleep(5)
        else:
            time.sleep(5)

main()
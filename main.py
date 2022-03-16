import time

import mysql.connector
import buzzer
import motor
import LDR
import weightsensor
import ir
import random

mydb = mysql.connector.connect(
    host="mysql.studev.groept.be",
    user="a21ib2c03",
    password="secret",
    database="a21ib1c03"
)
print(mydb)

mycursor = mydb.cursor()


def test_db():
    command = "UPDATE bird_feeder SET last_measured = 5 WHERE id = 'weight_sensor' "
    mycursor.execute(command)
    mydb.commit()

test_db()

def update(sensor_id, sensor_value):
    # updates last measured value
    command = "UPDATE bird_feeder SET last_measured = current_measured WHERE id = sensor_id"
    mycursor.execute(command)
    mydb.commit()

    # updates current measured value
    command = "UPDATE bird_feeder SET current_measured = %s WHERE id = %s"
    val = (sensor_value, sensor_id)
    mycursor.execute(command, val)
    mydb.commit()


def main():
    index = 0
    while True:
        # check whether there is light outside, if so, we can check the other sensors
        light_status = LDR.get_LDR_value()
        update('LDR', light_status)
        if light_status == 1:
            if index == 5:
                # update ir_sensor
                food_val = ir.check_food_status()
                update('ir_sensor', food_val)

            # update weight_sensor
            val = weightsensor.get_value()
            update('weight_sensor', val)
            allowed = weightsensor.allowed_bird(val,0,200)
            #we still need a way to acces this value, eventually in the birds database
            if allowed == 1:
                last_motor_state = "SELECT * from last_measured WHERE id = 'motor'" #0 or 1, closed and open

                if last_motor_state == 0:
                    motor.open_hatch()
                    update('motor', allowed)
            else:
                buzzer_on = "SELECT * from  WHERE component_on WHERE id = 'speaker'"
                if buzzer_on == 1:
                    buzzer.turn_on()
                last_motor_state = "SELECT * from last_measured WHERE id = 'motor'"
                if last_motor_state == 1:
                    motor.close_hatch()
                    update('motor', not allowed)

            index += 1
            time.sleep(1)
            if buzzer_on ==1:
                buzzer.turn_off()
        else:
            time.sleep(5)


# main()

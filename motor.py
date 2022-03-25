from gpiozero import Motor
import time

dcMotor = Motor (5, 6)

# def forwards():
#     dcMotor.forward()
#     print("forward dark")
#     return 1


# def backwards():
#     dcMotor.backward()
#     # print("backward light")
#     # return -1


# def stop():
#     dcMotor.stop()
#     print("stop")
#     return 0


def open():
    dcMotor.forward()
    time.sleep(3)
    dcMotor.stop()
    return 1


def close():
    dcMotor.backward()
    time.sleep(3)
    dcMotor.stop()
    return 0
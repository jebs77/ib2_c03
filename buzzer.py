from gpiozero import LED
import time


def buzzer():
    led = LED(13)
    frequency = 500
    cycles = 0

    while cycles < 2000:
        led.on()
        time.sleep(1 / frequency / 2)
        led.off()
        time.sleep(1 / frequency / 2)
        cycles = cycles + 1
        # print(cycles)

# buzzer()
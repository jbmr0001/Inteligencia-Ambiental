#!/usr/bin/env pybricks-micropython
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile
import pybricks

ev3 = EV3Brick()
motor = Motor(Port.B)

from umqtt.simple import MQTTClient

# Configurar el cliente MQTT
mqtt_server = "platinumvulture693.cloud.shiftr.io"
client_id = "EV3"
username = "*"
password = "*"

# Crear un objeto MQTTClient y conectarse al broker MQTT
client = MQTTClient(client_id, mqtt_server, user=username, password=password)
client.connect()

topic = "conectado"
message = "conectado"
client.publish(topic, message)

f = open('data.txt')
a=f.read()
print(a)
angulo=int(a)
f.close()
motor.reset_angle(angle=angulo)
def callback(topic, message):
    #print("Mensaje recibido en el tema {}: {}".format(topic, message))
    orden=str(message)
    ordenProcesada=orden[2:3]
    print(ordenProcesada)
    #ev3.speaker.beep()
    if ordenProcesada=="s":
        motor.run_target(speed=1000, target_angle=370, then=Stop.HOLD, wait=False) #aceleración progresiva
        #motor.track_target(speed=1000, target_angle=-2000, then=Stop.HOLD, wait=False)
        #print(motor.angle())
    if ordenProcesada=="b":
        motor.run_target(speed=1000, target_angle=-410, then=Stop.HOLD, wait=False) #aceleración progresiva

    if ordenProcesada=="o":
        motor.run_target(speed=1000, target_angle=0, then=Stop.HOLD, wait=False) #aceleración progresiva
    
    if ordenProcesada=="m":
        motor.run_target(speed=1000, target_angle=-250, then=Stop.HOLD, wait=False) #aceleración progresiva
    
    if ordenProcesada=="n":
        motor.run_target(speed=1000, target_angle=180, then=Stop.HOLD, wait=False) #aceleración progresiva
        
    if ordenProcesada=="p":
        ev3.speaker.beep()
        motor.hold()
        print(motor.angle())
        
    if ordenProcesada=="a":
        
        client.publish("posicionfinal", str(motor.angle()))
        print("Motor apagado, posicion final enviada: ",motor.angle())
        f = open('data.txt', 'w')
        f.write(str(motor.angle()))
        f.close()
        
        

    
client.set_callback(callback)
client.subscribe("subir")
client.subscribe("bajar")
client.subscribe("parar")
client.subscribe("apagar")
client.subscribe("mitad")
client.subscribe("mitad1")
client.subscribe("mitad2")
while True:
    client.check_msg()
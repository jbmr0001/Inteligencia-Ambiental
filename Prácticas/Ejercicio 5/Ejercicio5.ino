#include <SPI.h>
#include <WiFi101.h>
#include <MQTT.h>

char ssid[] = "*";
char pass[] = "*";

WiFiClient net;
MQTTClient client;

unsigned long lastMillis = 0;

void setup() {
  Serial.begin(9600);
  
  // start wifi and mqtt
  WiFi.begin(ssid, pass);
  client.begin("platinumvulture693.cloud.shiftr.io", net);
  client.onMessage(messageReceived);
  pinMode(1,OUTPUT);
  pinMode(3,OUTPUT);

  //connect();
}

void connect() {
  Serial.print("checking wifi...");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }

  Serial.print("\nconnecting...");
  while (!client.connect("arduino", "*", "*")) {
    Serial.print(".");
    delay(1000);
  }

  Serial.println("\nconnected!");

  client.subscribe("rojo");
  client.subscribe("amarillo");
}



void messageReceived(String &topic, String &payload) {
  if(topic=="rojo"){
    Serial.println(topic + ": " + payload);
    if(payload=="e"){
      digitalWrite(1,HIGH);
    }else{
      digitalWrite(1,LOW);
    }
  }
  if(topic=="amarillo"){
    Serial.println(topic + ": " + payload);
    if(payload=="e"){
      digitalWrite(3,HIGH);
    }else{
      digitalWrite(3,LOW);
    }
  }
}

void loop() {

  client.loop();
  delay(10);

  // check if connected
  if (!client.connected()) {
    connect();
  }

  // publish a message roughly every second.
  if (millis() - lastMillis > 1000) {
    lastMillis = millis();
    //client.publish("/hello", "world");
  }

  //digitalWrite(1,HIGH);
}

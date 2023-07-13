#define PUERTO_LED 6
#define PUERTO_A1 1
int val=0;

void setup() {
  // put your setup code here, to run once:
  pinMode(PUERTO_LED,OUTPUT);
  pinMode(A1,INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  analogReadResolution(8);
  val=2*analogRead(PUERTO_A1);
  digitalWrite(PUERTO_LED,HIGH);
  delay(1000-val);
  digitalWrite(PUERTO_LED,LOW);
  delay(1000-val);
}

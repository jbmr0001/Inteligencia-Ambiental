#define PUERTO_LED 6
#define PUERTO_A1 1
int val=0;
int umbral=125;

void setup() {
  // put your setup code here, to run once:
  pinMode(PUERTO_LED,OUTPUT);
  pinMode(A1,INPUT);
  analogReadResolution(8);
}

void loop() {
  // put your main code here, to run repeatedly:
  val=analogRead(PUERTO_A1);

  if (val>umbral){ 
    analogWrite(PUERTO_LED,val);
   }else{
    analogWrite(PUERTO_LED,LOW);
  }
  
}

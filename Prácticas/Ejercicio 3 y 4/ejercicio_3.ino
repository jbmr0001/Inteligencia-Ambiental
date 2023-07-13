#define PUERTO_A1 1
#define PUERTO_LED 6
String cadenaRecibida;
int umbral_inferior;
int umbral_superior;
int val;
String primeraParte;
String segundaParte;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(PUERTO_LED,OUTPUT);
  pinMode(A1,INPUT);
  cadenaRecibida="";
  umbral_inferior=0;
  umbral_superior=0;
  val=0;
  analogReadResolution(8);
}

void loop() {
  // put your main code here, to run repeatedly:
 if(Serial.available()){
  cadenaRecibida=Serial.readStringUntil(';');
   Serial.println("Cadena recibida: "+cadenaRecibida);
  
  primeraParte=cadenaRecibida.substring(0,cadenaRecibida.indexOf("="));
  if (primeraParte.equals("umbral_inferior")) {
    umbral_inferior=cadenaRecibida.substring(cadenaRecibida.indexOf("=")+1,cadenaRecibida.length()).toInt();
    Serial.println("Umbral inf: "+cadenaRecibida.substring(cadenaRecibida.indexOf("=")+1,cadenaRecibida.length()));
  }
  if ( primeraParte.equals("umbral_superior")) {
    umbral_superior=cadenaRecibida.substring(cadenaRecibida.indexOf("=")+1,cadenaRecibida.length()).toInt();
    Serial.println("Umbral sup: "+cadenaRecibida.substring(cadenaRecibida.indexOf("=")+1,cadenaRecibida.length()));
  }
 
 }
 val=analogRead(PUERTO_A1);
 if (val>umbral_inferior && val<umbral_superior){ 
    digitalWrite(PUERTO_LED,HIGH);
   }else{
    digitalWrite(PUERTO_LED,LOW);
  }
}

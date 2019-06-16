#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>


#define FIREBASE_HOST "iotsampleapp.firebaseio.com"             // the project name address from firebase id
#define FIREBASE_AUTH "P5Y3cpuPIsFXovCqTAxYGkdak9XrXPl6NlwDWKJb" 
#define WIFI_SSID "Honor 8X"                   // input your home or public wifi name 
#define WIFI_PASSWORD "devarsh123"             //password of wifi ssid

String fireStatus = "";  
int LED = D2; 
void setup() {
  Serial.begin(9600);
  delay(1000);
  pinMode(LED,OUTPUT);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);    
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);   
  Firebase.setString("data", "OFF");      

}

void loop() {
    fireStatus = Firebase.getString("data"); 
    fireStatus.toUpperCase();  
    Serial.println(fireStatus);
    if (fireStatus == "ON") {                                                          // compare the input of led status received from firebase
    Serial.println("Led Turned ON");                         
    digitalWrite(LED, HIGH);                                                  // make bultin led ON
                                              // make external led ON
  } 
  else if (fireStatus == "OFF") {                                                  // compare the input of led status received from firebase
    Serial.println("Led Turned OFF");
    digitalWrite(LED, LOW);                                               // make bultin led OFF
                                                        // make external led OFF
  }
  else {
    Serial.println("Wrong Credential! Please send ON/OFF");
  }
}

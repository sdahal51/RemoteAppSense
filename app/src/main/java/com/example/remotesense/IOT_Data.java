package com.example.remotesense;

public class IOT_Data {
    String Humidity, Temp_C, Temp_F, Time;

    public void GetData(){


    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getTemp_C() {
        return Temp_C;
    }

    public void setTemp_C(String temp_C) {
        Temp_C = temp_C;
    }

    public String getTemp_F() {
        return Temp_F;
    }

    public void setTemp_F(String temp_F) {
        Temp_F = temp_F;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

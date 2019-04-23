package com.example.myapplication.RemoteDataSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainDetails {

    @SerializedName("name")
    @Expose
    private String main;

    @SerializedName("temp")
    @Expose
    private String temp;

    @SerializedName("pressure")
    @Expose
    private String pressure;

    @SerializedName("humidity")
    @Expose
    private String humidity;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}

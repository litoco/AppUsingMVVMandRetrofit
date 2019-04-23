package com.example.myapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "city_weather")
public class CityWeatherDetails {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "wind_speed")
    private String windSpeed;

    @ColumnInfo(name = "temp")
    private String temperature;

    @ColumnInfo(name = "pressure")
    private String pressure;

    @ColumnInfo(name = "humidity")
    private String humidity;

    @ColumnInfo(name = "weather_desc")
    private String weatherDesc;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }
}

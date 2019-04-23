package com.example.myapplication.RemoteDataSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitiesWeatherData {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("weather")
    @Expose
    private WeatherDetails[] weather;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("main")
    @Expose
    private MainDetails main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherDetails[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherDetails[] weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public MainDetails getMain() {
        return main;
    }

    public void setMain(MainDetails main) {
        this.main = main;
    }
}

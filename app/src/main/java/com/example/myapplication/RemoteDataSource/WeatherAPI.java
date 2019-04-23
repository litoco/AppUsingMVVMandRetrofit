package com.example.myapplication.RemoteDataSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherAPI {

    @GET
    Call<CitiesWeatherData> getCitiesWeatherData(@Url String url);


}

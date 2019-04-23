package com.example.myapplication.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CityWeatherDetailsDao {

    @Query("SELECT * from city_weather WHERE city_name=:cityName")
    CityWeatherDetails getCityWeatherDetails(String cityName);

    @Query("SELECT * from city_weather WHERE city_name=:cityName")
    LiveData<List<CityWeatherDetails>> getCitiesAllWeatherDetails(String cityName);

    @Query("DELETE FROM city_weather")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM city_weather")
    int countRows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CityWeatherDetails cityWeatherDetails);
}

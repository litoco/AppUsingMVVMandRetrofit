package com.example.myapplication.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.myapplication.model.CityWeatherDetails;
import com.example.myapplication.repository.WeatherDetailRepository;

import java.util.List;

public class CityWeatherViewModel extends AndroidViewModel {

    private WeatherDetailRepository mWeatherDetailRepo;
    private LiveData<List<CityWeatherDetails>> mCitiesAllWeatherDetails;
    private static final String TAG = "CityWeatherViewModel";


    public CityWeatherViewModel(@NonNull Application application) {
        super(application);
        mWeatherDetailRepo = new WeatherDetailRepository(application);
    }

    public LiveData<List<CityWeatherDetails>>  getCitiesAllWeatherDetails(String cityName){
        mCitiesAllWeatherDetails = mWeatherDetailRepo.getAllWeatherData(cityName);
        return mCitiesAllWeatherDetails;
    }

}

package com.example.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.CityWeatherDetails;
import com.example.myapplication.viewmodels.CityWeatherViewModel;

import java.util.List;

public class WeatherDetailsActivity extends AppCompatActivity {

    private ImageView backButton;
    public String cityNameString;
    private TextView cityName, humidity, pressure, temperature, weatherDetails, windSpeed;
    private LinearLayout cityNameHolder, humidityHolder, pressureHolder, temperatureHolder, weatherDetailsHolder, windSpeedHolder;
    private CityWeatherViewModel mCityWeatherViewModel;
    private ProgressBar progressBar;
    private static final String TAG = "WeatherDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        initVars();

        startProgressBar();
        cityNameString = getIntent().getStringExtra("cityName");
        mCityWeatherViewModel = ViewModelProviders.of(this).get(CityWeatherViewModel.class);

        try {
            mCityWeatherViewModel.getCitiesAllWeatherDetails(cityNameString).observe(this, new Observer<List<CityWeatherDetails>>() {
                @Override
                public void onChanged(@Nullable List<CityWeatherDetails> cityWeatherDetails) {
                    if(cityWeatherDetails!=null && cityWeatherDetails.size()!=0) {
                        populateVars(cityWeatherDetails.get(0));
                    }else{
                        Toast.makeText(WeatherDetailsActivity.this, "Fetching data...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch (NullPointerException e){
            Log.w(TAG, "onChanged: "+e.getMessage());
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void populateVars(CityWeatherDetails cityWeatherDetails) {
        stopProgressBar();
        cityName.setText(cityWeatherDetails.getCityName());
        pressure.setText(cityWeatherDetails.getPressure());
        humidity.setText(cityWeatherDetails.getHumidity());
        temperature.setText(cityWeatherDetails.getTemperature());
        weatherDetails.setText(cityWeatherDetails.getWeatherDesc());
        windSpeed.setText(cityWeatherDetails.getWindSpeed());
    }

    private void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        cityNameHolder.setVisibility(View.GONE);
        humidityHolder.setVisibility(View.GONE);
        pressureHolder.setVisibility(View.GONE);
        temperatureHolder.setVisibility(View.GONE);
        weatherDetailsHolder.setVisibility(View.GONE);
        windSpeedHolder.setVisibility(View.GONE);
    }

    private void stopProgressBar() {
        progressBar.setVisibility(View.GONE);
        cityNameHolder.setVisibility(View.VISIBLE);
        humidityHolder.setVisibility(View.VISIBLE);
        pressureHolder.setVisibility(View.VISIBLE);
        temperatureHolder.setVisibility(View.VISIBLE);
        weatherDetailsHolder.setVisibility(View.VISIBLE);
        windSpeedHolder.setVisibility(View.VISIBLE);
    }

    private void initVars() {
        progressBar = findViewById(R.id.progress_bar);
        cityNameHolder = findViewById(R.id.city_name_container);
        humidityHolder = findViewById(R.id.humidity_container);
        pressureHolder = findViewById(R.id.pressure_container);
        temperatureHolder = findViewById(R.id.temperature_container);
        weatherDetailsHolder = findViewById(R.id.weather_description_container);
        windSpeedHolder = findViewById(R.id.wind_speed_container);
        backButton = findViewById(R.id.back_arrow_button);
        cityName = findViewById(R.id.city_name);
        humidity = findViewById(R.id.humidity);
        pressure = findViewById(R.id.pressure);
        temperature = findViewById(R.id.temperature);
        weatherDetails = findViewById(R.id.weather_description);
        windSpeed = findViewById(R.id.wind_speed);
    }
}

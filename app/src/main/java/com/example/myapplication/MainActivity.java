package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText enterCityName;
    private ImageView searchCityWeather;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterCityName = findViewById(R.id.enter_city_name);
        searchCityWeather = findViewById(R.id.search_city_weather);

        searchCityWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherDetailsActivity.class);
                intent.putExtra("cityName", enterCityName.getText().toString());
                startActivity(intent);

            }
        });

    }
}

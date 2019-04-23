package com.example.myapplication.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {CityWeatherDetails.class}, version = 1, exportSchema = false)
public abstract class WeatherDetailsRoomDatabase extends RoomDatabase {

    private static final String TAG = "WeatherDetailsRoomData";


    private static volatile WeatherDetailsRoomDatabase instance;

    public static WeatherDetailsRoomDatabase getInstance(Context context){
        if(instance==null){
            synchronized (WeatherDetailsRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDetailsRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract CityWeatherDetailsDao cityWeatherDetailDao();
}

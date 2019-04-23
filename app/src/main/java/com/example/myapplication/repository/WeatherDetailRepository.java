package com.example.myapplication.repository;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.RemoteDataSource.CitiesWeatherData;
import com.example.myapplication.RemoteDataSource.WeatherAPI;
import com.example.myapplication.WeatherDetailsActivity;
import com.example.myapplication.model.CityWeatherDetails;
import com.example.myapplication.model.CityWeatherDetailsDao;
import com.example.myapplication.model.WeatherDetailsRoomDatabase;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetailRepository {

    private static final String TAG = "WeatherDetailRepository";
    private final String BASE_URL = "https://samples.openweathermap.org/";
    private SharedPreferences sharedPreferences;
    private Context context;
    private CityWeatherDetailsDao cityWeatherDetailsDao;
    private LiveData<List<CityWeatherDetails>> cityWeatherDetailsLiveData;

    public WeatherDetailRepository(@NonNull Application application){
        context = application.getApplicationContext();
        sharedPreferences = application.getSharedPreferences("sp", context.MODE_PRIVATE);
        WeatherDetailsRoomDatabase db = WeatherDetailsRoomDatabase.getInstance(context);
        cityWeatherDetailsDao = db.cityWeatherDetailDao();
    }

    public LiveData<List<CityWeatherDetails>> getAllWeatherData(String cityName){
        Long timeInMilli = (new Date()).getTime();
        Long prevTimeInMilli = sharedPreferences.getLong("time", 0);
        if((timeInMilli-prevTimeInMilli)>86400){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("time", timeInMilli);
            editor.apply();
            getDataFromUrl(cityName);
        }
         cityWeatherDetailsLiveData = getDataFromRoom(cityName);
        return cityWeatherDetailsLiveData;
    }

    private LiveData<List<CityWeatherDetails>> getDataFromRoom(String cityName) {
        return cityWeatherDetailsDao.getCitiesAllWeatherDetails(cityName);
    }

    public void getDataFromUrl(final String cityName){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        String url ="data/2.5/weather?q="+cityName;
        url += "&appid=b6907d289e10d714a6e88b30761fae22";
        Call<CitiesWeatherData> call = weatherAPI.getCitiesWeatherData(url);

        call.enqueue(new Callback<CitiesWeatherData>() {
            @Override
            public void onResponse(Call<CitiesWeatherData> call, Response<CitiesWeatherData> response) {
                if(response.body()!=null) {
                    CityWeatherDetails cityWeatherDetails = new CityWeatherDetails();
                    cityWeatherDetails.setCityName(response.body().getName());
                    cityWeatherDetails.setHumidity(response.body().getMain().getHumidity());
                    cityWeatherDetails.setPressure(response.body().getMain().getPressure()+" Pa");
                    cityWeatherDetails.setTemperature(response.body().getMain().getTemp()+" K");
                    cityWeatherDetails.setWeatherDesc(response.body().getWeather()[0].getMain()
                            + ", "+response.body().getWeather()[0].getDescription());
                    cityWeatherDetails.setWindSpeed(response.body().getWind().getSpeed()+" MPH");

                    insertDataIntoRoom(cityWeatherDetails);
//                    ((WeatherDetailsActivity)context).populateVars(cityWeatherDetails);
                }else{
                    Toast.makeText(context, "No data for: "+cityName, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CitiesWeatherData> call, Throwable t) {
                Log.e(TAG, "error: "+t.getMessage());
            }
        });
    }

    private void insertDataIntoRoom(CityWeatherDetails cityWeatherDetails){
        insertAsyncTask asyncTask =
        new insertAsyncTask(cityWeatherDetailsDao);
        asyncTask.execute(cityWeatherDetails);
    }

    private static class insertAsyncTask extends AsyncTask<CityWeatherDetails, Void, Void> {

        private CityWeatherDetailsDao mAsyncTaskDao;

        insertAsyncTask(CityWeatherDetailsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CityWeatherDetails... params) {
            mAsyncTaskDao.deleteAll();
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}

package com.example.myapplication.RemoteDataSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {


    @SerializedName("speed")
    @Expose
    private String speed;


    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}

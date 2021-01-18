package com.geekbrains.simpleweather.data;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private int currentTempDay;
    private int currentTempNight;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String cityName, int tempDay, int tempNight) {
        this.cityName = cityName;
        this.currentTempDay = tempDay;
        this.currentTempNight = tempNight;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCurrentTempDay() {
        return currentTempDay;
    }

    public void setCurrentTempDay(int currentTempDay) {
        this.currentTempDay = currentTempDay;
    }

    public int getCurrentTempNight() {
        return currentTempNight;
    }

    public void setCurrentTempNight(int currentTempNight) {
        this.currentTempNight = currentTempNight;
    }
}

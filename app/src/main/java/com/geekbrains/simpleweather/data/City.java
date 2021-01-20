package com.geekbrains.simpleweather.data;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName;
    private WeatherInfo currentWeather;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String cityName, WeatherInfo weatherInfo) {
        this.cityName = cityName;
        this.currentWeather = weatherInfo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public WeatherInfo getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherInfo currentWeather) {
        this.currentWeather = currentWeather;
    }
}

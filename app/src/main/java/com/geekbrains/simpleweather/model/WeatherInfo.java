package com.geekbrains.simpleweather.model;

import java.io.Serializable;

enum WindDirection {
    N("N"),
    NW("NW"),
    W("W"),
    SW("SW"),
    S("S"),
    SE("SE"),
    E("E"),
    NE("NE");
    private String dierction;

    WindDirection(String dierction) {
        this.dierction = dierction;
    }

    public String getDierction() {
        return dierction;
    }
}

public class WeatherInfo implements Serializable {

    private int currentTempDay;
    private int currentTempNight;
    private int humidity;
    private WindDirection windDirection;
    private double windSpeed;
    private double pressure;

    public WeatherInfo(int currentTempDay,
                       int currentTempNight,
                       int humidity,
                       WindDirection windDirection,
                       double windSpeed,
                       double pressure) {
        this.currentTempDay = currentTempDay;
        this.currentTempNight = currentTempNight;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
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

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}



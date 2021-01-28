
package com.geekbrains.simpleweather.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastResponce implements Serializable {

    private final static long serialVersionUID = 2177741907493334569L;

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private int message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private WeatherForecast[] weatherForecast;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public WeatherForecast[] getWeatherForecast() {
        return weatherForecast;
    }

    public List<WeatherForecast> getWeatherForecastDaily() {
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        for (WeatherForecast forecast : weatherForecast) {
            if (forecast.getDtTxt().contains("12:00:00"))
                weatherForecasts.add(forecast);
        }
        return weatherForecasts;
    }

    public void setWeatherForecast(WeatherForecast[] weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public City getCity() {
        return city;
    }

    public String getCityName() {
        return city == null ? "" : city.getName();
    }

    public void setCity(City city) {
        this.city = city;
    }

}

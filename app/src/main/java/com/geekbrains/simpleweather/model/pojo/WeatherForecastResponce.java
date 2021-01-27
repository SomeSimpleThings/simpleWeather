
package com.geekbrains.simpleweather.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherForecastResponce implements Serializable {

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
    private final static long serialVersionUID = 2177741907493334569L;

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

    public void setWeatherForecast(WeatherForecast[] weatherForecast) {
        this.weatherForecast = weatherForecast;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}

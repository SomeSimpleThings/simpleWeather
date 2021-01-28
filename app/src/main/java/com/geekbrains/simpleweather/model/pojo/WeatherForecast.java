
package com.geekbrains.simpleweather.model.pojo;

import android.text.format.DateFormat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherForecast implements Serializable {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private Weather weather;
    @SerializedName("weather")
    @Expose
    private WeatherDescription[] weatherDescription;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("pop")
    @Expose
    private double pop;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("snow")
    @Expose
    private Snow snow;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    private final static long serialVersionUID = 8466358075120258203L;

    public int getDt() {
        return dt;
    }

    public String getDtFormatted() {
        return (DateFormat.format("dd.MM.yy", (long) dt * 1000)).toString();
    }


    public void setDt(int dt) {
        this.dt = dt;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public WeatherDescription[] getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherDescriptionReadable() {
        if (weatherDescription.length > 0)
            return weatherDescription[0].getDescription();
        return "";
    }

    public void setWeatherDescription(WeatherDescription[] weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

}

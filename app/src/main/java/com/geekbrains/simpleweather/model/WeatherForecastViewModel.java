package com.geekbrains.simpleweather.model;

import androidx.lifecycle.ViewModel;

import com.geekbrains.simpleweather.api.WeatherRepository;

public class WeatherForecastViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;

    public WeatherForecastViewModel() {
        this.weatherRepository = new WeatherRepository();
    }
}

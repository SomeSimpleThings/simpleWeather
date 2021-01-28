package com.geekbrains.simpleweather.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geekbrains.simpleweather.api.WeatherRepository;
import com.geekbrains.simpleweather.model.pojo.WeatherForecast;
import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecastViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;
    private final MutableLiveData<List<WeatherForecastResponce>> data;
    private final MutableLiveData<List<WeatherForecast>> dailyForecast;
    private final MutableLiveData<WeatherForecastResponce> selectedResponce;

    public WeatherForecastViewModel() {
        this.weatherRepository = new WeatherRepository();
        data = new MutableLiveData<>(new ArrayList<>());
        selectedResponce = new MutableLiveData<>();
        dailyForecast = new MutableLiveData<>();
    }

    public void postForecastSearch(String city) {
        WeatherForecastResponce forecastResponce = weatherRepository.getWeatherForecast(city);
        selectedResponce.postValue(forecastResponce);
        if (forecastResponce != null) {
            data.getValue().add(forecastResponce);
        }
        data.postValue(data.getValue());
    }

    public void firstLaunchHack(String city) {
        WeatherForecastResponce forecastResponce = weatherRepository.getWeatherForecast(city);
        selectedResponce.postValue(forecastResponce);
        dailyForecast.postValue(forecastResponce.getWeatherForecastDaily());
    }

    public MutableLiveData<List<WeatherForecastResponce>> getWeatherForecastResponses() {
        return data;
    }

    public MutableLiveData<List<WeatherForecast>> getWeatherForecast() {
        return dailyForecast;
    }

    public void selectCity(WeatherForecastResponce responce) {
        dailyForecast.postValue(responce.getWeatherForecastDaily());
        selectedResponce.postValue(responce);
    }

    public MutableLiveData<WeatherForecastResponce> getSelectedResponce() {
        return selectedResponce;
    }
}

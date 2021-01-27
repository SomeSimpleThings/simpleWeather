package com.geekbrains.simpleweather.model;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CityViewModel extends ViewModel {

    private final MutableLiveData<City> selectedCity = new MutableLiveData<>();
    List<City> cities = new ArrayList<>();
    private MutableLiveData<List<City>> favouriteCities;

    WeatherInfo weatherInfo1 = new WeatherInfo(20, 15, 75, WindDirection.N, 4, 760);
    WeatherInfo weatherInfo2 = new WeatherInfo(13, 8, 90, WindDirection.SE, 7, 740);

    public void selectCity(City item) {
        selectedCity.setValue(item);
    }

    public LiveData<City> getSelectedCity() {
        return selectedCity;
    }

    public MutableLiveData<List<City>> getFavouriteCities() {
        if (favouriteCities == null) {
            favouriteCities = new MutableLiveData<>();
            loadFavourites();
        }
        return favouriteCities;
    }

    private void loadFavourites() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            populate();
            favouriteCities.setValue(cities);
        }, 1000);
    }

    private void populate() {

        cities.add(new City("Владимир", weatherInfo1));
        cities.add(new City("Петропавловск Камчатский", weatherInfo1));
        cities.add(new City("Москва", weatherInfo2));
        cities.add(new City("Санкт Петербург", weatherInfo2));
        cities.add(new City("Иваново", weatherInfo2));
    }

    public void addCity(String text) {
        cities.add(new City(text, weatherInfo2));
        favouriteCities.postValue(cities);
    }
}

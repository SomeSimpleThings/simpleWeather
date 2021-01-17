package com.geekbrains.simpleweather.data;

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
            long seed = System.nanoTime();
            favouriteCities.setValue(cities);
        }, 1000);
    }

    private void populate() {
        cities.add(new City("Владимир", 20, 15));
        cities.add(new City("Петропавловск Камчатский", 15, 8));
        cities.add(new City("Москва", 25, 10));
        cities.add(new City("Санкт Петербург", 25, 10));
        cities.add(new City("Иваново", 18, 9));
    }

    public void addCity(String text) {
        cities.add(new City(text, 20, 10));
        favouriteCities.postValue(cities);
    }
}

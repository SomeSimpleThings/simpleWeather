package com.geekbrains.simpleweather.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.HttpsURLConnection;

public class WeatherRepository {

    private static final int READ_TIMEOUT = 10000;


    private final MutableLiveData<WeatherForecastResponce> weatherForecastResponceLiveData;
    private final ExecutorService executorService;

    public WeatherRepository() {
        executorService = Executors.newSingleThreadExecutor();
        weatherForecastResponceLiveData = new MutableLiveData<>();
    }

    public LiveData<WeatherForecastResponce> getWeatherForecast(String city) {
        Future<WeatherForecastResponce> responce = executorService.submit(() ->
                getWeatherForecastResponce(city)
        );
        try {
            weatherForecastResponceLiveData.postValue(responce.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return weatherForecastResponceLiveData;
    }

    private WeatherForecastResponce getWeatherForecastResponce(String city) {
        String result = null;
        try {
            URL uri = new URL(ApiConstants.getForecastUrl(city));
            HttpsURLConnection urlConnection = (HttpsURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(READ_TIMEOUT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            result = getLines(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result == null ? null : new Gson().fromJson(result, WeatherForecastResponce.class);
    }

    private String getLines(BufferedReader in) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ignored) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}

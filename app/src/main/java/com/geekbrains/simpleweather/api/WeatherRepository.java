package com.geekbrains.simpleweather.api;

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


    private final ExecutorService executorService;

    public WeatherRepository() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public WeatherForecastResponce getWeatherForecast(String city) {
        Future<WeatherForecastResponce> responce = executorService.submit(() ->
                getWeatherForecastResponce(city)
        );
        try {
            return responce.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}

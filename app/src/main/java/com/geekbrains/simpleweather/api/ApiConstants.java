package com.geekbrains.simpleweather.api;

public class ApiConstants {

    public static final String LOCALE = "ru";
    public static final String UNITS = "metric";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String QUERY_PARAMS = "q=%s&units=%s&lang=%s&appid=%s";
    private static final String FORECAST_URL = BASE_URL + "forecast?" + QUERY_PARAMS;
    private static final String CURRENT_WEATHER_URL = BASE_URL + "weather?" + QUERY_PARAMS;

    public static final String OPENWEATHER_API_KEY = "aeda831c879480708dfb5e9bc4e5f74d";


    private ApiConstants() {
    }

    public static String getCurrentWeatherUrl(String city) {
//        return String.format(CURRENT_WEATHER_URL, city, UNITS, LOCALE, BuildConfig.OPENWEATHER_API_KEY);
        return String.format(CURRENT_WEATHER_URL, city, UNITS, LOCALE, OPENWEATHER_API_KEY);
    }

    public static String getForecastUrl(String city) {
//        return String.format(FORECAST_URL, city, UNITS, LOCALE, BuildConfig.OPENWEATHER_API_KEY);
        return String.format(FORECAST_URL, city, UNITS, LOCALE, OPENWEATHER_API_KEY);
    }
}

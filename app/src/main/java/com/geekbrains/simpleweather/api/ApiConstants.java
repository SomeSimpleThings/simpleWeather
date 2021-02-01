package com.geekbrains.simpleweather.api;

public class ApiConstants {

    public static final String LOCALE = "ru";
    public static final String UNITS = "metric";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String QUERY_PARAMS = "q=%s&units=%s&lang=%s&appid=%s";
    private static final String FORECAST_URL = BASE_URL + "forecast?" + QUERY_PARAMS;
    private static final String CURRENT_WEATHER_URL = BASE_URL + "weather?" + QUERY_PARAMS;
    public static final String OPENWEATHER_API_KEY = "9a5449b35d68c5ec2ca0a4c71ee3ea6f";


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

package com.geekbrains.simpleweather.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InstantSaverImplementation {

    private static InstantSaverImplementation instance;
    private final HashMap<String, List<String>> values;

    private InstantSaverImplementation() {
        values = new HashMap<>();

    }

    public static InstantSaverImplementation getInstance() {
        if (instance == null) instance = new InstantSaverImplementation();
        return instance;
    }

    public List<String> getSavedList(String key) {
        if (values.containsKey(key)) return values.get(key);
        else return new ArrayList<>();
    }

    public void saveList(String key, List<String> value) {
        values.put(key, value);
    }
}

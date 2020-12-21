package com.geekbrains.simpleweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> days = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateDaysList();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_daily);
        DaysWeatherAdapter adapter = new DaysWeatherAdapter(this, days);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void populateDaysList() {
        days.add(getString(R.string.monday));
        days.add(getString(R.string.tuesday));
        days.add(getString(R.string.wednesday));
        days.add(getString(R.string.thursday));
        days.add(getString(R.string.friday));
        days.add(getString(R.string.saturday));
        days.add(getString(R.string.sunday));
    }
}
package com.geekbrains.simpleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tagg";
    ArrayList<String> days = new ArrayList<>();
    BottomDrawerFragment bottomDrawerFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateDaysList();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_daily);
        DaysWeatherAdapter adapter = new DaysWeatherAdapter(this, days);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        BottomAppBar bar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            bottomDrawerFragment = new BottomDrawerFragment();
            bottomDrawerFragment.show(getSupportFragmentManager(), TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
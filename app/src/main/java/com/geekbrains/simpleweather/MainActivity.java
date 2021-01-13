package com.geekbrains.simpleweather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_SELECT_CITY = 42;
    private static final String TAG = "tagg";
    public static final String CITY_KEY = "selected_city_key";
    ArrayList<String> days = new ArrayList<>();
    BottomDrawerFragment bottomDrawerFragment;
    TextView currentCity;

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

        currentCity = findViewById(R.id.tv_city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                showBottomDrawer();
                return true;
            case R.id.settings_menu_drawer:
                showSettingsActivity();
                return true;
            case R.id.info_menu_drawer:
                openCityInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openCityInfo() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, currentCity.getText());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void showSettingsActivity() {
        Intent settingsIntent = new Intent(this,
                SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void showBottomDrawer() {
        bottomDrawerFragment = new BottomDrawerFragment();
        bottomDrawerFragment.show(getSupportFragmentManager(), TAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQ_CODE_SELECT_CITY == requestCode
                && resultCode == RESULT_OK
                && data != null) {
            String selectedCity = data.getStringExtra(CITY_KEY);
            currentCity.setText(selectedCity);
            bottomDrawerFragment.dismiss();
        }
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

    public void startSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, REQ_CODE_SELECT_CITY);
    }
}
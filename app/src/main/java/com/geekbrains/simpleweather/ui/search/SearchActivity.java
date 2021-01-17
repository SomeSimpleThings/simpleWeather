package com.geekbrains.simpleweather.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.common.InstantSaverImplementation;

import java.util.ArrayList;

import timber.log.Timber;

import static com.geekbrains.simpleweather.ui.MainActivity.CITY_KEY;

public class SearchActivity extends AppCompatActivity {

    ImageButton searchButton;
    EditText editText;
    ArrayList<String> cities = new ArrayList<>();
    SearchCitiesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        showToastAndLog("On create");
        cities.addAll(InstantSaverImplementation.getInstance().getSavedList("cities"));

        RecyclerView recyclerView = findViewById(R.id.recyclerview_cities);
        adapter = new SearchCitiesAdapter(this, cities, this::onItemClicked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        setupButton();
        editText = findViewById(R.id.edit_text_city_user_input);
    }

    @Override
    protected void onStart() {
        showToastAndLog("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        showToastAndLog("onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        showToastAndLog("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        showToastAndLog("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        showToastAndLog("onDestroy");
        InstantSaverImplementation.getInstance().saveList("cities", cities);

        super.onDestroy();
    }

    private void showToastAndLog(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        Timber.d(text);
    }


    private void setupButton() {
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            cities.add(text);
            adapter.notifyDataSetChanged();
        });
    }

    public void onItemClicked(String city) {
        Intent intent = new Intent();
        intent.putExtra(CITY_KEY, city);
        setResult(RESULT_OK, intent);
        finish();
    }
}
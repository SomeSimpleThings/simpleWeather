package com.geekbrains.simpleweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import timber.log.Timber;

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
        adapter = new SearchCitiesAdapter(this, cities);
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
}
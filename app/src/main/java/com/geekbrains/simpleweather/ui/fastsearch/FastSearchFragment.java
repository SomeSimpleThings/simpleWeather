package com.geekbrains.simpleweather.ui.fastsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.WeatherForecastViewModel;
import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;


public class FastSearchFragment extends DialogFragment {
    private WeatherForecastViewModel viewModel;
    private EditText searchEditText;
    private WeatherForecastResponce previous;

    public FastSearchFragment() {
        // Required empty public constructor
    }

    public static FastSearchFragment newInstance() {
        return new FastSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fast_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchEditText = view.findViewById(R.id.edit_text_city_user_input);
        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this::searchButtonClick);

        viewModel = new ViewModelProvider(requireActivity()).get(WeatherForecastViewModel.class);
        viewModel.getSelectedResponce().observe(requireActivity(), this::onResponce);
    }

    private void searchButtonClick(View view) {
        String text = searchEditText.getText().toString();
        if (!text.equals(""))
            viewModel.postForecastSearch(text);
        searchEditText.setText("");
    }

    private void onResponce(WeatherForecastResponce responce) {
        if (responce != null) {
            if (previous != null && !previous.equals(responce)) {
                viewModel.selectCity(responce);
                this.dismiss();
            } else previous = responce;
        } else {
            searchEditText.setError("City not found!");
        }
    }
}
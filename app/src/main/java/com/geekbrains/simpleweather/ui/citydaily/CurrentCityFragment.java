package com.geekbrains.simpleweather.ui.citydaily;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.WeatherForecastViewModel;
import com.geekbrains.simpleweather.model.pojo.WeatherForecast;
import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;
import com.geekbrains.simpleweather.ui.BottomDrawerFragment;
import com.geekbrains.simpleweather.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.geekbrains.simpleweather.ui.MainActivity.BACKSTACK_KEY;


public class CurrentCityFragment extends Fragment {

    private static final String TAG = "tagg";

    private TextView currentCity;
    WeatherForecastViewModel weatherForecastViewModel;

    public CurrentCityFragment() {
        // Required empty public constructor
    }

    public static CurrentCityFragment newInstance() {
        return new CurrentCityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getView();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_city, container, false);
        weatherForecastViewModel = new ViewModelProvider(requireActivity()).get(WeatherForecastViewModel.class);
        setupRecyclerView(view);
        return view;
    }

    private void setupOrientation(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.current_city_linear);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherForecastViewModel.getSelectedResponce()
                .observe(getViewLifecycleOwner(), responce -> {
                    if (responce != null)
                        updateCityWeatherCard(view, responce);
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BottomNavigationView bar = getActivity().findViewById(R.id.bottom_nav);
        bar.setVisibility(View.VISIBLE);
        bar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.getmenu_drawer) {
                showBottomDrawer();
                return true;
            }
            if (item.getItemId() == R.id.info_menu_drawer) {
                openCityInfo();
                return true;
            }
            if (item.getItemId() == R.id.favourite_menu_drawer) {
                showSearchFragment();
                return true;
            }
            if (item.getItemId() == android.R.id.home) {
                showBottomDrawer();
                return true;
            }
            return false;
        });
    }

    private void updateCityWeatherCard(@NonNull View view, WeatherForecastResponce responce) {
        TextView textViewCity = view.findViewById(R.id.tv_city);
        TextView textViewTempDay = view.findViewById(R.id.tv_temp_day);
        TextView textViewTempNight = view.findViewById(R.id.tv_temp_night);
        TextView textViewWindSpeed = view.findViewById(R.id.tv_wind_speed);
        TextView textViewWindDirection = view.findViewById(R.id.tv_wind_direction);
        TextView textViewHumidity = view.findViewById(R.id.tv_humidity);
        TextView textViewPressure = view.findViewById(R.id.tv_pressure);

        textViewCity.setText(responce.getCityName());
        WeatherForecast weather = responce.getWeatherForecast()[0];
        textViewTempDay.setText(getString(R.string.temp_default,
                weather.getWeather().getTempMax()));
        textViewTempNight.setText(getString(R.string.temp_default,
                weather.getWeather().getTempMin()));
        textViewWindSpeed.setText(String.valueOf(weather.getWind().getSpeed()));
        textViewWindDirection.setText(String.valueOf(weather.getWind().getDeg()));
        textViewHumidity.setText(getString(R.string.humidity_default,
                weather.getWeather().getHumidity()));
        textViewPressure.setText(String.valueOf(weather.getWeather().getPressure()));
    }

    private void setupRecyclerView(@NonNull View view) {

        currentCity = view.findViewById(R.id.tv_city);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_daily);
        DaysWeatherAdapter adapter = new DaysWeatherAdapter(getActivity());
        weatherForecastViewModel.getWeatherForecast()
                .observe(getViewLifecycleOwner(), adapter::setForecasts);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(
                ResourcesCompat.getDrawable(getResources(),
                        R.drawable.recycler_divider,
                        null));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.drawer_menu, menu);
    }

    private void showBottomDrawer() {
        BottomDrawerFragment bottomDrawerFragment = new BottomDrawerFragment();
        bottomDrawerFragment.show(getParentFragmentManager(), TAG);
    }

    private void showSearchFragment() {
        SearchFragment searchFragment = SearchFragment.newInstance();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, searchFragment)
                .addToBackStack(BACKSTACK_KEY)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void openCityInfo() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, currentCity.getText());
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
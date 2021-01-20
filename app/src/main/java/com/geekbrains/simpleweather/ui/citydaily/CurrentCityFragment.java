package com.geekbrains.simpleweather.ui.citydaily;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.data.City;
import com.geekbrains.simpleweather.data.CityViewModel;
import com.geekbrains.simpleweather.ui.BottomDrawerFragment;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class CurrentCityFragment extends Fragment {

    private static final String TAG = "tagg";

    private TextView currentCity;
    private final ArrayList<String> days = new ArrayList<>();

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
        setupOrientation(view);
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
        setupRecyclerView(view);
        CityViewModel viewModel = new ViewModelProvider(requireActivity()).get(CityViewModel.class);
        viewModel.getSelectedCity().observe(getViewLifecycleOwner(), city -> updateCityWeatherCard(view, city));
    }

    private void updateCityWeatherCard(@NonNull View view, City city) {
        Resources res = getResources();
        TextView textViewCity = view.findViewById(R.id.tv_city);
        TextView textViewTempDay = view.findViewById(R.id.tv_temp_day);
        TextView textViewTempNight = view.findViewById(R.id.tv_temp_night);
        TextView textViewWindSpeed = view.findViewById(R.id.tv_wind_speed);
        TextView textViewWindDirection = view.findViewById(R.id.tv_wind_direction);
        TextView textViewHumidity = view.findViewById(R.id.tv_humidity);
        TextView textViewPressure = view.findViewById(R.id.tv_pressure);
        textViewCity.setText(city.getCityName());
        textViewTempDay.setText(res.getString(R.string.temp_default,
                city.getCurrentWeather().getCurrentTempDay()));
        textViewTempNight.setText(getString(R.string.temp_default,
                city.getCurrentWeather().getCurrentTempNight()));
        textViewWindSpeed.setText(String.valueOf(city.getCurrentWeather().getWindSpeed()));
        textViewWindDirection.setText(String.valueOf(city.getCurrentWeather().getWindDirection()));
        textViewHumidity.setText(res.getString(R.string.humidity_default,
                city.getCurrentWeather().getHumidity()));
        textViewPressure.setText(String.valueOf(city.getCurrentWeather().getPressure()));
    }

    private void setupRecyclerView(@NonNull View view) {
        currentCity = view.findViewById(R.id.tv_city);
        populateDaysList();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_daily);
        DaysWeatherAdapter adapter = new DaysWeatherAdapter(getActivity(), days);
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
        BottomAppBar bar = getActivity().findViewById(R.id.bottom_app_bar);
        bar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        bar.setNavigationOnClickListener(v -> showBottomDrawer());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.info_menu_drawer) {
            openCityInfo();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            showBottomDrawer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBottomDrawer() {
        BottomDrawerFragment bottomDrawerFragment = new BottomDrawerFragment();
        bottomDrawerFragment.show(getParentFragmentManager(), TAG);
    }

    private void openCityInfo() {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, currentCity.getText());
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
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
}
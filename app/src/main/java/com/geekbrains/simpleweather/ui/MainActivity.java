package com.geekbrains.simpleweather.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.WeatherForecastViewModel;
import com.geekbrains.simpleweather.ui.citydaily.CurrentCityFragment;
import com.geekbrains.simpleweather.ui.favourites.FavouritesFragment;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final String BACKSTACK_KEY = "back_stack_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//// TODO: 27.01.21 replace with search by coordinates
        WeatherForecastViewModel weatherForecastViewModel
                = new ViewModelProvider(this).get(WeatherForecastViewModel.class);
        weatherForecastViewModel.firstLaunchHack("Moscow");

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, getCurrentFragment(fragmentManager))
                .commit();

    }

    @NotNull
    private Fragment getCurrentFragment(FragmentManager fragmentManager) {
        Fragment currentFragment;
        Timber.d(String.valueOf(fragmentManager.getBackStackEntryCount()));
        if (fragmentManager.getBackStackEntryCount() > 0) {
            currentFragment = FavouritesFragment.newInstance();
        } else {
            currentFragment = CurrentCityFragment.newInstance();
        }
        return currentFragment;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.exit_confirm))
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, (dialog, id) -> MainActivity.this.finish())
                    .setNegativeButton(R.string.no, (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
        } else super.onBackPressed();
    }
}
package com.geekbrains.simpleweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.ui.about.AboutFragment;
import com.geekbrains.simpleweather.ui.fastsearch.FastSearchFragment;
import com.geekbrains.simpleweather.ui.settings.SettingsActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import static com.geekbrains.simpleweather.ui.MainActivity.BACKSTACK_KEY;


public class BottomDrawerFragment extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.bottom_drawer_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        processNavigation(view.findViewById(R.id.navigation_view));
        view.findViewById(R.id.image_close_drawer).setOnClickListener(v -> this.dismiss());
    }

    private void processNavigation(NavigationView drawerNavigation) {
        drawerNavigation.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.search_menu_drawer) {
                showFastSearchDialog();
            } else if (menuItem.getItemId() == R.id.about_menu_drawer) {
                showAboutFragment();
            } else if (menuItem.getItemId() == R.id.settings_menu_drawer) {
                startSettingsActivity();
            }
            this.dismiss();
            return true;
        });
    }

    private void showFastSearchDialog() {
        FastSearchFragment fastSearchFragment = FastSearchFragment.newInstance();
        fastSearchFragment.show(getParentFragmentManager(), "dialogCustom");
    }

    private void startSettingsActivity() {
        Intent settingsIntent = new Intent(getActivity(),
                SettingsActivity.class);
        startActivity(settingsIntent);
    }


    private void showAboutFragment() {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, aboutFragment)
                .addToBackStack(BACKSTACK_KEY)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
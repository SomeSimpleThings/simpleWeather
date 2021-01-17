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
import com.geekbrains.simpleweather.ui.search.SearchFragment;
import com.geekbrains.simpleweather.ui.settings.SettingsActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;


public class BottomDrawerFragment extends BottomSheetDialogFragment {


    public static final String BACKSTACK_KEY = "back_stack_key";

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
                showSearchFragment();
            } else if (menuItem.getItemId() == R.id.settings_menu_drawer) {
                startSettingsActivity();
            }
            this.dismiss();
            return true;
        });
    }

    private void startSettingsActivity() {
        Intent settingsIntent = new Intent(getActivity(),
                SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void showSearchFragment() {
        SearchFragment currentCityFragment = SearchFragment.newInstance();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, currentCityFragment)
                .addToBackStack(BACKSTACK_KEY)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
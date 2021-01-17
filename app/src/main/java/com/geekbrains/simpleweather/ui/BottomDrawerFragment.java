package com.geekbrains.simpleweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geekbrains.simpleweather.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class BottomDrawerFragment extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.bottom_drawer_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NavigationView drawerNavigation = getView().findViewById(R.id.navigation_view);

        drawerNavigation.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.search_menu_drawer) {
                Activity activity = getActivity();
                if (activity != null) ((MainActivity) activity).startSearchActivity();
                return true;
            }
            return true;
        });
    }
}

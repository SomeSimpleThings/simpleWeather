package com.geekbrains.simpleweather.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geekbrains.simpleweather.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButton(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_fragment_menu, menu);
        BottomAppBar bar = getActivity().findViewById(R.id.bottom_app_bar);
        bar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        bar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
    }

    private void setupButton(View view) {
        Button githubButton = view.findViewById(R.id.ic_github_button);
        githubButton.setOnClickListener(v -> {
            Intent browserIntent =
                    new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.prefs_ghub_link)));
            if (browserIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(browserIntent);

            }
        });
    }
}
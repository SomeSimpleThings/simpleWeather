package com.geekbrains.simpleweather.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.google.android.material.bottomappbar.BottomAppBar;

public class SearchFragment extends Fragment implements SearchCitiesAdapter.OnItemClickListener {

    private EditText editText;
    private SearchCitiesAdapter adapter;
    private CityViewModel viewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButton(view);
        viewModel = new ViewModelProvider(requireActivity()).get(CityViewModel.class);
        setupRecylerView(view, viewModel);
        editText = view.findViewById(R.id.edit_text_city_user_input);
    }

    private void setupRecylerView(@NonNull View view, CityViewModel viewModel) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_cities);
        adapter = new SearchCitiesAdapter(getContext(), this);
        viewModel.getFavouriteCities().observe(requireActivity(), adapter::setCities);
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
        inflater.inflate(R.menu.search_fragment_menu, menu);
        BottomAppBar bar = getActivity().findViewById(R.id.bottom_app_bar);
        bar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        bar.setHideOnScroll(true);
        bar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupButton(View view) {
        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (text != null && !text.equals(""))
                viewModel.addCity(text);
            editText.setText("");
        });
    }

    public void onItemClicked(City city) {
        viewModel.selectCity(city);
        getActivity().onBackPressed();
    }
}
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.WeatherForecastViewModel;
import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

public class SearchFragment extends Fragment {

    private EditText editText;
    private SearchCitiesAdapter adapter;
    private WeatherForecastViewModel viewModel;

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
        setupRecylerView(view);
        editText = view.findViewById(R.id.edit_text_city_user_input);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> getActivity().onBackPressed());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BottomNavigationView bar = getActivity().findViewById(R.id.bottom_nav);
        bar.setVisibility(View.GONE);
    }

    private void setupRecylerView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_cities);
        adapter = new SearchCitiesAdapter(getContext(), this::onItemClicked, this::onItemRemoved);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherForecastViewModel.class);
        viewModel.getWeatherForecastResponses().observe(requireActivity(),
                weatherForecastResponces -> adapter.setCityList(weatherForecastResponces));
        viewModel.getSelectedResponce().observe(requireActivity(), responce -> {
            if (responce == null) {
                showSnackIfError(view);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(Objects.requireNonNull(ResourcesCompat.getDrawable(getResources(),
                R.drawable.recycler_divider,
                null)));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_fragment_menu, menu);
    }

    private void setupButton(View view) {
        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            String text = editText.getText().toString();
            if (!text.equals(""))
                viewModel.postForecastSearch(text);
            editText.setText("");
        });
    }

    public void onItemClicked(WeatherForecastResponce responce) {
        viewModel.selectCity(responce);
        getActivity().onBackPressed();
    }

    public void onItemRemoved(WeatherForecastResponce responce) {
        showUndoSnackbar(responce);
    }

    private void showUndoSnackbar(WeatherForecastResponce responce) {
        Snackbar snackbar = Snackbar.make(getView(), R.string.undo_delete_text,
                LENGTH_LONG);
        snackbar.setAnchorView(R.id.fab);
        snackbar.setAction(R.string.snack_bar_undo, v -> adapter.addItem(responce));
        snackbar.show();
    }

    private void showSnackIfError(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.city_not_found,
                LENGTH_LONG);
        snackbar.setAnchorView(R.id.fab);
        snackbar.show();
    }
}
package com.geekbrains.simpleweather.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.data.City;

import java.util.ArrayList;
import java.util.List;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<City> cities;
    private final OnItemClickListener itemClickListener;

    SearchCitiesAdapter(Context context, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = listener;
        this.cities = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cities.get(position);
        holder.bind(city, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityNameView;
        TextView cityDayWeather;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameView = itemView.findViewById(R.id.tv_city_recycler_item);
            cityDayWeather = itemView.findViewById(R.id.tv_current_temp_recycler_item);
        }

        public void bind(City city, OnItemClickListener itemClickListener) {
            cityNameView.setText(city.getCityName());
            cityDayWeather.setText(String.valueOf(city.getCurrentTempDay()));
            itemView.setOnClickListener(v -> itemClickListener.onItemClicked(city));
        }
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onItemClicked(City city);
    }
}

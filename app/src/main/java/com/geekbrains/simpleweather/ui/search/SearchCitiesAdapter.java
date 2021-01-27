package com.geekbrains.simpleweather.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.City;

import java.util.ArrayList;
import java.util.List;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<City> cityList;
    private final OnItemClickListener itemClickListener;
    private final OnItemRemovedListener itemRemovedListener;
    private int deletedPosition;

    SearchCitiesAdapter(Context context,
                        OnItemClickListener clickListener,
                        OnItemRemovedListener removedListener) {
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = clickListener;
        this.itemRemovedListener = removedListener;
        this.cityList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.bind(city, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }


    public void deleteItem(int adapterPosition) {
        City deletedCity = cityList.get(adapterPosition);
        deletedPosition = adapterPosition;
        cityList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        itemRemovedListener.onItemRemoved(deletedCity);
    }

    public void addItem(City city) {
        cityList.add(deletedPosition,
                city);
        notifyItemInserted(deletedPosition);
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
            cityDayWeather.setText(String.valueOf(city.getCurrentWeather().getCurrentTempDay()));
            itemView.setOnClickListener(v -> itemClickListener.onItemClicked(city));
        }
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onItemClicked(City city);
    }

    interface OnItemRemovedListener {
        void onItemRemoved(City city);
    }
}

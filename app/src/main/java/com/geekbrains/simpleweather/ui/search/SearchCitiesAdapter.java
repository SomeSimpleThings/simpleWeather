package com.geekbrains.simpleweather.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.pojo.WeatherForecast;
import com.geekbrains.simpleweather.model.pojo.WeatherForecastResponce;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<WeatherForecastResponce> weatherForecastList;
    private final OnItemClickListener itemClickListener;
    private final OnItemRemovedListener itemRemovedListener;
    private int deletedPosition;

    SearchCitiesAdapter(Context context,
                        OnItemClickListener clickListener,
                        OnItemRemovedListener removedListener) {
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = clickListener;
        this.itemRemovedListener = removedListener;
        this.weatherForecastList = new ArrayList<>();
        this.deletedPosition = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherForecastResponce city = weatherForecastList.get(position);
        holder.bind(city, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return weatherForecastList.size();
    }


    public void deleteItem(int adapterPosition) {
        WeatherForecastResponce deleted = weatherForecastList.get(adapterPosition);
        deletedPosition = adapterPosition;
        weatherForecastList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        itemRemovedListener.onItemRemoved(deleted);
    }

    public void addItem(WeatherForecastResponce responce) {
        weatherForecastList.add(deletedPosition,
                responce);
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

        public void bind(WeatherForecastResponce weatherForecastResponce, OnItemClickListener itemClickListener) {
            cityNameView.setText(weatherForecastResponce.getCityName());
            if (weatherForecastResponce.getWeatherForecast() != null
                    && weatherForecastResponce.getWeatherForecast().length != 0) {
                WeatherForecast forecast = weatherForecastResponce.getWeatherForecast()[0];
                if (forecast != null) {
                    cityDayWeather.setText(String.format(Locale.getDefault(),
                            "%1$.0f°", forecast.getWeather().getTemp()));
                }
            }
            itemView.setOnClickListener(v -> itemClickListener.onItemClicked(weatherForecastResponce));
        }
    }

    public void setCityList(List<WeatherForecastResponce> cityList) {
        this.weatherForecastList = cityList;
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onItemClicked(WeatherForecastResponce responce);
    }

    interface OnItemRemovedListener {
        void onItemRemoved(WeatherForecastResponce responce);
    }
}

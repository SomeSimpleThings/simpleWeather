package com.geekbrains.simpleweather.ui.citydaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;
import com.geekbrains.simpleweather.model.pojo.WeatherForecast;

import java.util.List;
import java.util.Locale;

public class DaysWeatherAdapter extends RecyclerView.Adapter<DaysWeatherAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<WeatherForecast> forecasts;

    public DaysWeatherAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.day_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherForecast forecast = forecasts.get(position);
        holder.setForecast(forecast);
    }

    public void setForecasts(List<WeatherForecast> weatherForecasts) {
        forecasts = weatherForecasts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return forecasts == null ? 0 : forecasts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayofweekTextView;
        TextView tempDayTextView;
        TextView descriptinTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayofweekTextView = itemView.findViewById(R.id.tv_day_of_week_recycler_item);
            tempDayTextView = itemView.findViewById(R.id.tv_temp_day_recycler_item);
            descriptinTextView = itemView.findViewById(R.id.tv_description_recycler_item);
        }

        public void setForecast(WeatherForecast forecast) {
            dayofweekTextView.setText(forecast.getDtFormatted());
            tempDayTextView.setText(String.format(Locale.getDefault(),
                    "%1$.0f°", forecast.getWeather().getTemp()));
            descriptinTextView.setText(String.valueOf(forecast.getWeatherDescriptionReadable()));
        }
    }
}

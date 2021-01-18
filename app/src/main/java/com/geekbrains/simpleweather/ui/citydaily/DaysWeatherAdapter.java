package com.geekbrains.simpleweather.ui.citydaily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.simpleweather.R;

import java.util.List;

public class DaysWeatherAdapter extends RecyclerView.Adapter<DaysWeatherAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<String> days;

    public DaysWeatherAdapter(Context context, List<String> days) {
        this.days = days;
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
        String day = days.get(position);
        holder.dayOfWeek.setText(day);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayOfWeek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.tv_day_of_week_recycler_item);
        }
    }
}

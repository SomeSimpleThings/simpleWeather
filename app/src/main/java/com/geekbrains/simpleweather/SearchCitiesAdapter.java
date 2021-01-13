package com.geekbrains.simpleweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<String> cities;
    private final OnItemClickListener itemClickListener;

    SearchCitiesAdapter(Context context, List<String> cities, OnItemClickListener listener) {
        this.cities = cities;
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.city_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String city = cities.get(position);
        holder.bind(city, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView cityView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityView = itemView.findViewById(R.id.tv_city_recycler_item);
        }

        public void bind(String city, OnItemClickListener itemClickListener) {
            cityView.setText(city);
            itemView.setOnClickListener(v -> itemClickListener.onItemClicked(city));
        }
    }

    interface OnItemClickListener {
        void onItemClicked(String city);
    }
}

package com.example.android.sunshine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private List<String> mWeatherData;
    private ForecastAdapterOnClickHandler mClickHandler;

    interface ForecastAdapterOnClickHandler{
        void onClick(final String weatherForDay);
    }

    public ForecastAdapter(ForecastAdapterOnClickHandler clickHandler) {
        this.mClickHandler = clickHandler;
    }
    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(this.mWeatherData.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherData == null ? 0 : mWeatherData.size();
    }

    public ForecastAdapter setWeatherData(List<String> weatherData) {
        this.mWeatherData = weatherData;
        notifyDataSetChanged();
        return this;
    }


    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView mWeatherTextView;

        ForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String weatherForDay = mWeatherData.get(adapterPosition);
            mClickHandler.onClick(weatherForDay);
        }

    }


}

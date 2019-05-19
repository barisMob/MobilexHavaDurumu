package com.example.mobilexhavadurumu.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.common.Utils;
import com.example.mobilexhavadurumu.models.WeatherResponse;
import com.example.mobilexhavadurumu.screens.home.HomeListItemMvcImpl;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.mobilexhavadurumu.common.Utils.getCurrentDay;
import static com.example.mobilexhavadurumu.common.Utils.setWeatherState;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    HomeListItemMvcImpl viewMvc;
    private List<WeatherResponse> mNames = new ArrayList<>();

    public HomeListAdapter(List<WeatherResponse> mNames) {
        this.mNames = mNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder:called");
        viewMvc = new HomeListItemMvcImpl(
                LayoutInflater.from(viewGroup.getContext()), viewGroup
        );
        return new ViewHolder(viewMvc.getRootView());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder:called");
        SimpleDateFormat format = new SimpleDateFormat("HH:MM", Locale.US);


        viewHolder.mTxtCityName.setText(mNames.get(i).getName());
        viewHolder.mTxtHour.setText(format.format(new Date()));
        viewHolder.mTxtDay.setText(getCurrentDay());
        viewHolder.mTxtTemp.setText(mNames.get(i).getResponse().getTemp().substring(0, 2) + "° C");
        viewHolder.mTxtMaxTemp.setText(mNames.get(i).getResponse().getTemp_max().substring(0, 2) + "° C");
        viewHolder.mTxtMinTemp.setText(mNames.get(i).getResponse().getTemp_min().substring(0, 2) + "° C");
        viewHolder.mTxtHisTemp.setText(mNames.get(i).getResponse().getTemp().substring(0, 2) + "° C");
        viewHolder.mTxtNem.setText(mNames.get(i).getResponse().getHumidity());
        viewHolder.mTxtMess.setText(mNames.get(i).getResponse().getPressure());
        viewHolder.mTxtRuzgar.setText(mNames.get(i).getWind().getSpeed() + "/" + mNames.get(i).getWind().getDeg() + "°");
        viewHolder.mImgWeatherState.setColorFilter(Color.GRAY);
        
        viewHolder.mImgWeatherState.setImageResource(setWeatherState(mNames.get(i).getWeather().get(0).getMain()));



    }



    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public void setWeather(List<WeatherResponse> mNames) {

        this.mNames = mNames;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTxtCityName;
        private final TextView mTxtDay;
        private final TextView mTxtHour;
        private final TextView mTxtTemp;
        private final ImageView mImgWeatherState;
        private final TextView mTxtMaxTemp;
        private final TextView mTxtMinTemp;
        private final TextView mTxtHisTemp;
        private final TextView mTxtNem;
        private final TextView mTxtRuzgar;
        private final TextView mTxtMess;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgWeatherState = itemView.findViewById(R.id.img_weather_state);
            mTxtCityName = itemView.findViewById(R.id.txt_city_name);
            mTxtDay = itemView.findViewById(R.id.txt_day);
            mTxtHour = itemView.findViewById(R.id.txt_hour);
            mTxtTemp = itemView.findViewById(R.id.txt_temperature);
            mTxtMaxTemp = itemView.findViewById(R.id.txt_max_temp);
            mTxtMinTemp = itemView.findViewById(R.id.txt_min_temp);
            mTxtHisTemp = itemView.findViewById(R.id.txt_hissedilen);
            mTxtNem = itemView.findViewById(R.id.txt_nem);
            mTxtRuzgar = itemView.findViewById(R.id.txt_ruzgar);
            mTxtMess = itemView.findViewById(R.id.txt_mes);

        }
    }
}

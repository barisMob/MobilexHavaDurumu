package com.example.mobilexhavadurumu.screens.home;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.common.Constants;
import com.example.mobilexhavadurumu.location.GPSTracker;
import com.example.mobilexhavadurumu.location.LocationClient;
import com.example.mobilexhavadurumu.models.LocationResponse;
import com.example.mobilexhavadurumu.models.WeatherResponse;
import com.example.mobilexhavadurumu.networking.WeatherApi;
import com.example.mobilexhavadurumu.networking.WeatherApiClient;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.mobilexhavadurumu.common.Utils.startLoading;
import static com.example.mobilexhavadurumu.common.Utils.stopLoading;

public class HomeMvcImpl extends BaseViewMvc implements HomeViewMvc {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private WeatherApi mWeatherApi;
    private LocationResponse locationResponse = null;


    public HomeMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {

        setRootView(inflater.inflate(R.layout.fragment_home, parent, false));

        if (getGpsCase()) {
            GPSTracker gps = new GPSTracker(getContext());

            locationResponse = new LocationResponse(String.valueOf(gps.getLatitude()),String.valueOf(gps.getLongitude()));
            Toast.makeText(getContext(), locationResponse.getLat() + "   " + locationResponse.getLon(), Toast.LENGTH_SHORT).show();
        } else {
            fetcWeathherCondition();

        }
    }

    @Override
    public void fetcWeathherCondition() {
        mWeatherApi = WeatherApiClient.getClient().create(WeatherApi.class);

        startLoading(getContext(), "Hava durumu bilgileri yükleniyor.");

        mWeatherApi.fetchWeatherState("izmir" + " ,tr")
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        stopLoading();
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), response.body().getName(), Toast.LENGTH_SHORT).show();

                        } else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        stopLoading();
                        networkCallFailed();
                    }
                });

    }

    @Override
    public void networkCallFailed() {
        Toast.makeText(getContext(), R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();

    }


}

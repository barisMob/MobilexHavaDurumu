package com.example.mobilexhavadurumu.networking;

import com.example.mobilexhavadurumu.common.Constants;
import com.example.mobilexhavadurumu.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather?appid="+Constants.WEATHER_API_KEY)
    Call<WeatherResponse> fetchWeatherState(@Query("q") String cityName);
}

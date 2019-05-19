package com.example.mobilexhavadurumu.networking;

import com.example.mobilexhavadurumu.models.AllCitiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllCitiesApi {

    @GET("cities/")
    Call<AllCitiesResponse> fetchAllCities();
}

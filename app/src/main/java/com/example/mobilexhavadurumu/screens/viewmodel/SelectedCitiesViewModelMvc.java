package com.example.mobilexhavadurumu.screens.viewmodel;

import android.arch.lifecycle.LiveData;

import com.example.mobilexhavadurumu.models.SelectedCities;

import java.util.List;

public interface SelectedCitiesViewModelMvc {
    void insert(SelectedCities selectedCities);
    void delete(SelectedCities selectedCities);
    void deleteAll();
    LiveData<List<SelectedCities>> getAllSelectedCities();
}

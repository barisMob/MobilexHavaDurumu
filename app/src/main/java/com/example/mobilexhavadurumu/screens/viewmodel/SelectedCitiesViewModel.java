package com.example.mobilexhavadurumu.screens.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.repository.SelectedCitiesRepository;

import java.util.List;

public class SelectedCitiesViewModel extends AndroidViewModel implements SelectedCitiesViewModelMvc {
    private SelectedCitiesRepository selectedCitiesRepository;
    private LiveData<List<SelectedCities>> allSelectedCities;

    public SelectedCitiesViewModel(@NonNull Application application) {
        super(application);
        selectedCitiesRepository = new SelectedCitiesRepository(application);
        allSelectedCities = selectedCitiesRepository.getSelectedCitiesLiveData();
    }

    @Override
    public void insert(SelectedCities selectedCities) {
        selectedCitiesRepository.insert(selectedCities);
    }


    @Override
    public void delete(SelectedCities selectedCities) {
        selectedCitiesRepository.delete(selectedCities);
    }

    @Override
    public void deleteAll() {
        selectedCitiesRepository.deleteAllRequestToken();
    }

    @Override
    public LiveData<List<SelectedCities>> getAllSelectedCities() {
        return allSelectedCities;
    }
}

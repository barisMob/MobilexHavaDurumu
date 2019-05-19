package com.example.mobilexhavadurumu.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SelectedCitiesDao {

    @Insert
    void insert(SelectedCities note);

    @Query("DELETE FROM selected_city_table WHERE selected_city_name ==:note")
    void delete(String note);

    @Query("DELETE FROM selected_city_table")
    void deleteAllRequestToken();

    @Query("SELECT * FROM selected_city_table")
    LiveData<List<SelectedCities>> getSelectedCities();
}

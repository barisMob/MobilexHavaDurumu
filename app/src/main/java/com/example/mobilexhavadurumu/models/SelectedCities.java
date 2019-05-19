package com.example.mobilexhavadurumu.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "selected_city_table")
public class SelectedCities {
    @PrimaryKey(autoGenerate = true)
    private int temp_id;

    private String selected_city_name;


    public SelectedCities(String selected_city_name) {
        this.selected_city_name = selected_city_name;
    }

    public int getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(int temp_id) {
        this.temp_id = temp_id;
    }

    public String getSelected_city_name() {
        return selected_city_name;
    }

}

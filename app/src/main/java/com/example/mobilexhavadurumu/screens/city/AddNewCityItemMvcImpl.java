package com.example.mobilexhavadurumu.screens.city;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;

public class AddNewCityItemMvcImpl extends BaseViewMvc {

    public AddNewCityItemMvcImpl(LayoutInflater inflater, ViewGroup parent) {

        setRootView(inflater.from(parent.getContext()).inflate(R.layout.cities_list_item, parent, false));

    }
}

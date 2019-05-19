package com.example.mobilexhavadurumu.screens.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;

public class HomeListItemMvcImpl extends BaseViewMvc implements HomeListItemMvc{


    public HomeListItemMvcImpl(LayoutInflater inflater, ViewGroup parent)
    {
        setRootView(inflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false));
    }


}

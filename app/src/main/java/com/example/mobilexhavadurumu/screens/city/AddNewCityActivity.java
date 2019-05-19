package com.example.mobilexhavadurumu.screens.city;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mobilexhavadurumu.R;

import static com.example.mobilexhavadurumu.common.Utils.StartActivity;

public class AddNewCityActivity extends AppCompatActivity {

    private AddNewCityViewMvc mViewMvc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = new AddNewCityMvcImpl(LayoutInflater.from(this), null);
        setContentView(((AddNewCityMvcImpl) mViewMvc).getRootView());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}

package com.example.mobilexhavadurumu.screens.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

public class BaseViewMvc implements ViewMvc {
    private View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public boolean getGpsCase() {
        SharedPreferences sp = getContext().getSharedPreferences("gps_prefs", Activity.MODE_PRIVATE);
        return sp.getBoolean("gps_case", false);
    }

    protected void setRootView(View mRootView) {
        this.mRootView = mRootView;
    }

    protected <T extends View> T findViewById(int id) {

        return getRootView().findViewById(id);
    }

    protected Context getContext() {
        return getRootView().getContext();
    }

}

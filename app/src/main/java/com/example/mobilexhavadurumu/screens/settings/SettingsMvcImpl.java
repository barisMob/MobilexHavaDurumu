package com.example.mobilexhavadurumu.screens.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;

public class SettingsMvcImpl extends BaseViewMvc implements SettingsViewMvc {

    Switch swGps;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SettingsMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.fragment_settings, parent, false));
        swGps = findViewById(R.id.switchGps);
        swGps.setChecked(getGpsCase());
        swGps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp = getContext().getSharedPreferences("gps_prefs", Activity.MODE_PRIVATE);
                editor = sp.edit();
                editor.putBoolean("gps_case", isChecked);
                editor.commit();
            }
        });
    }

}

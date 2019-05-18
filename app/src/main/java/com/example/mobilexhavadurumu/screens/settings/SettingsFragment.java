package com.example.mobilexhavadurumu.screens.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mobilexhavadurumu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {


    private SettingsViewMvc mViewMvc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mViewMvc = new SettingsMvcImpl(inflater, null);

        return ((SettingsMvcImpl) mViewMvc).getRootView();
    }

}

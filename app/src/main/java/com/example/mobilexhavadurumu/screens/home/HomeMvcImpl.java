package com.example.mobilexhavadurumu.screens.home;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.adapters.HomeListAdapter;
import com.example.mobilexhavadurumu.location.GPSTracker;
import com.example.mobilexhavadurumu.models.LocationResponse;
import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.models.WeatherResponse;
import com.example.mobilexhavadurumu.networking.WeatherApi;
import com.example.mobilexhavadurumu.networking.WeatherApiClient;
import com.example.mobilexhavadurumu.screens.city.AddNewCityActivity;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;
import com.example.mobilexhavadurumu.screens.viewmodel.SelectedCitiesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.mobilexhavadurumu.common.Utils.StartActivity;
import static com.example.mobilexhavadurumu.common.Utils.startLoading;
import static com.example.mobilexhavadurumu.common.Utils.stopLoading;

public class HomeMvcImpl extends BaseViewMvc implements HomeViewMvc {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private WeatherApi mWeatherApi;
    private LocationResponse locationResponse = null;
    private List<SelectedCities> mSelectedCities = new ArrayList<>();
    private ArrayList<String> mSelectedCityNames = new ArrayList<>();
    private List<WeatherResponse> mWeatherResponseList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclHomeWeather;
    private HomeListAdapter mHomeListAdaper;
    private Boolean firstLoading = true;

    private SelectedCitiesViewModel selectedCitiesViewModel;


    public HomeMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {

        setRootView(inflater.inflate(R.layout.fragment_home, parent, false));
        floatingActionButton = findViewById(R.id.fab_add_city);
        initRecyclerView();
        initFloatingButton();
        initSelectedCitiesViewModel();


        if (getGpsCase()) {
            GPSTracker gps = new GPSTracker(getContext());
            // Weather apı lokasyona gore servis işleminde dogru sonuc vermedıgı için enlem ve boylamı almama ragmen kullanmama kararı aldım.
            //Otomatik gps acıksa izmiri secicek.
            locationResponse = new LocationResponse(String.valueOf(gps.getLatitude()), String.valueOf(gps.getLongitude()));
        }
    }

    @Override
    public void fetcWeathherCondition(String cityName) {
        mWeatherApi = WeatherApiClient.getClient().create(WeatherApi.class);

        if(firstLoading){
            startLoading(getContext(), "Hava durumu bilgileri yükleniyor.");
            firstLoading=false;

        }

        mWeatherApi.fetchWeatherState(cityName + " ,tr")
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                        if (response.isSuccessful()) {
                            mWeatherResponseList.add(response.body());
                            if (mWeatherResponseList.size() == mSelectedCities.size()) {
                                setWeatherResponseList();
                                mHomeListAdaper.setWeather(mWeatherResponseList);
                                stopLoading();
                            }

                        } else {
                            stopLoading();
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        stopLoading();
                        networkCallFailed();
                    }
                });

    }

    @Override
    public void networkCallFailed() {
        Toast.makeText(getContext(), R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();

    }

    private void addArrayList() {
        mSelectedCityNames.add("Barış");
        mSelectedCityNames.add("Başar");
        mSelectedCityNames.add("Sevda");
        mSelectedCityNames.add("Mehmet");
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclHomeWeather = findViewById(R.id.recylerViewHome);
        mRecyclHomeWeather.setLayoutManager(layoutManager);
        mHomeListAdaper = new HomeListAdapter(mWeatherResponseList);
        mRecyclHomeWeather.setAdapter(mHomeListAdaper);

    }

    private void initFloatingButton() {

        floatingActionButton = findViewById(R.id.fab_add_city);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                StartActivity(getContext(), AddNewCityActivity.class, null);

            }
        });
    }

    private void initSelectedCitiesViewModel() {

        selectedCitiesViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(SelectedCitiesViewModel.class);
        selectedCitiesViewModel.getAllSelectedCities().observe((LifecycleOwner) getContext(), new Observer<List<SelectedCities>>() {
            @Override
            public void onChanged(@Nullable List<SelectedCities> selectedCities) {
                mSelectedCities.clear();
                mSelectedCities.addAll(selectedCities);
                for (int i = 0; i < selectedCities.size(); i++) {
                    fetcWeathherCondition(selectedCities.get(i).getSelected_city_name());
                }

            }
        });
    }

    private void setWeatherResponseList() {
        WeatherResponse tempFirst = mWeatherResponseList.get(0);
        int index = 0;
        for (int i = 0; i < mWeatherResponseList.size(); i++) {

            if (mWeatherResponseList.get(i).getName().equals("İzmir")) {

                index = i;
                break;
            }
        }
        mWeatherResponseList.set(0, mWeatherResponseList.get(index));
        mWeatherResponseList.set(index, tempFirst);
    }
}

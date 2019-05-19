package com.example.mobilexhavadurumu.screens.city;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.adapters.CitiesListAdapter;
import com.example.mobilexhavadurumu.models.AllCitiesResponse;
import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.models.WeatherResponse;
import com.example.mobilexhavadurumu.networking.AllCitiesApi;
import com.example.mobilexhavadurumu.networking.AllCitiesClient;
import com.example.mobilexhavadurumu.networking.WeatherApi;
import com.example.mobilexhavadurumu.networking.WeatherApiClient;
import com.example.mobilexhavadurumu.screens.common.BaseViewMvc;
import com.example.mobilexhavadurumu.screens.viewmodel.SelectedCitiesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mobilexhavadurumu.common.Utils.startLoading;
import static com.example.mobilexhavadurumu.common.Utils.stopLoading;

public class AddNewCityMvcImpl extends BaseViewMvc implements AddNewCityViewMvc {


    private  List<SelectedCities> mSelectedCities = new ArrayList<>();

    private  List<AllCitiesResponse.Data> mAllCities = new ArrayList<>();
    private  List<AllCitiesResponse.Data> mAllCitiesTemp = new ArrayList<>();

    private AllCitiesApi mAllCitiesApi;
    private SelectedCitiesViewModel selectedCitiesViewModel;

    private SearchView mSrcVvw;

    private RecyclerView mRecyclCities;
    private CitiesListAdapter mCitiesListAdaper;
    private boolean firstTime= true;

    public AddNewCityMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.activity_add_new_city, parent, false));
        initSearchView();
        fetchAllCities();
    }

    @Override
    public void fetchAllCities() {

        mAllCitiesApi = AllCitiesClient.getClient().create(AllCitiesApi.class);

        startLoading(getContext(), "Şehirler listesi yükleniyor.");

        
        mAllCitiesApi.fetchAllCities()
                .enqueue(new Callback<AllCitiesResponse>() {
                    @Override
                    public void onResponse(Call<AllCitiesResponse> call, Response<AllCitiesResponse> response) {
                        stopLoading();
                        if (response.isSuccessful()) {
                            mAllCities.addAll(response.body().getData());
                            mAllCitiesTemp.addAll(response.body().getData());
                            initSelectedCitiesViewModel();

                        } else {
                            Toast.makeText(getContext(), R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();                        }
                    }

                    @Override
                    public void onFailure(Call<AllCitiesResponse> call, Throwable t) {
                        stopLoading();
                        Toast.makeText(getContext(), R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void initSelectedCitiesViewModel(){

        selectedCitiesViewModel = ViewModelProviders.of((FragmentActivity) getContext()).get(SelectedCitiesViewModel.class);
        selectedCitiesViewModel.getAllSelectedCities().observe((LifecycleOwner) getContext(), new Observer<List<SelectedCities>>() {
            @Override
            public void onChanged(@Nullable List<SelectedCities> selectedCities) {
                mSelectedCities.clear();
                mSelectedCities.addAll(selectedCities);
                if(firstTime){
                    initRecyclerView();
                    firstTime =false;
                }

            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclCities = findViewById(R.id.recylerViewCities);
        mRecyclCities.setLayoutManager(layoutManager);
        mCitiesListAdaper = new CitiesListAdapter(getContext(),mAllCitiesTemp,mSelectedCities);
        mRecyclCities.setAdapter(mCitiesListAdaper);
    }

    private void initSearchView(){

        mSrcVvw=findViewById(R.id.search);
        mSrcVvw.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchList(newText);
                if (newText.isEmpty()) {
                    mCitiesListAdaper.setCities(mAllCities,mSelectedCities);
                }

                return false;
            }
        });
    }

    private void searchList(String keyword) {

        mAllCitiesTemp.clear();
        for (int i = 0; i < mAllCities.size(); i++) {
            if (mAllCities.get(i).getName().toLowerCase().contains(keyword.toLowerCase())) {
                mAllCitiesTemp.add(mAllCities.get(i));
            }
        }
        mCitiesListAdaper.setCities(mAllCitiesTemp,mSelectedCities);
    }
}

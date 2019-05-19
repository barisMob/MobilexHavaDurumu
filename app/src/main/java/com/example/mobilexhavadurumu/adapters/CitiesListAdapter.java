package com.example.mobilexhavadurumu.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mobilexhavadurumu.R;
import com.example.mobilexhavadurumu.models.AllCitiesResponse;
import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.screens.city.AddNewCityItemMvcImpl;
import com.example.mobilexhavadurumu.screens.viewmodel.SelectedCitiesViewModel;


import java.util.ArrayList;
import java.util.List;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    AddNewCityItemMvcImpl viewMvc;

    private List<AllCitiesResponse.Data> allCitiesResponse = new ArrayList<>();
    private List<SelectedCities> selectedCities = new ArrayList<>();
    private SelectedCitiesViewModel selectedCitiesViewModel;


    public CitiesListAdapter(Context cntx , List<AllCitiesResponse.Data> allCitiesResponses, List<SelectedCities> selectedCities) {
        this.allCitiesResponse = allCitiesResponses;
        this.selectedCities = selectedCities;
        this.selectedCitiesViewModel = ViewModelProviders.of((FragmentActivity) cntx).get(SelectedCitiesViewModel.class);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder:called");
        viewMvc = new AddNewCityItemMvcImpl(
                LayoutInflater.from(viewGroup.getContext()), viewGroup
        );
        return new ViewHolder(viewMvc.getRootView());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder:called");

        viewHolder.mTxtCityName.setText(allCitiesResponse.get(i).getName());
        viewHolder.mCbSelectedCity.setChecked(chekSelected(allCitiesResponse.get(i).getName()));
        viewHolder.mCbSelectedCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewHolder.mCbSelectedCity.isChecked()){
                    selectedCitiesViewModel.insert(new SelectedCities(allCitiesResponse.get(i).getName()));
                }
                else{
                    selectedCitiesViewModel.delete(new SelectedCities(allCitiesResponse.get(i).getName()));
                }
                if(allCitiesResponse.get(i).getName().equals("İzmir")){
                    selectedCitiesViewModel.insert(new SelectedCities(allCitiesResponse.get(i).getName()));
                }
            }
        });
//        viewHolder.mCbSelectedCity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    selectedCitiesViewModel.insert(new SelectedCities(allCitiesResponse.get(i).getName()));
//                }
//                else{
//                    selectedCitiesViewModel.delete(new SelectedCities(allCitiesResponse.get(i).getName()));
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return allCitiesResponse.size();
    }

    private boolean chekSelected(String cityName) {

        for (int i = 0; i < selectedCities.size(); i++) {
            if (selectedCities.get(i).getSelected_city_name().equals(cityName)) {
                return true;
            }
        }
        return false;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTxtCityName;
        private final CheckBox mCbSelectedCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtCityName = itemView.findViewById(R.id.txt_city_name);
            mCbSelectedCity = itemView.findViewById(R.id.cb_selected_city);


        }
    }

    public void setCities(List<AllCitiesResponse.Data> allCitiesResponses, List<SelectedCities> selectedCities) {

        this.allCitiesResponse = allCitiesResponses;
        this.selectedCities = selectedCities;
        notifyDataSetChanged();
    }

}

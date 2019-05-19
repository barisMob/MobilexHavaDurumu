package com.example.mobilexhavadurumu.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mobilexhavadurumu.database.SelectedCitiesDatabase;
import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.models.SelectedCitiesDao;

import java.util.List;

public class SelectedCitiesRepository {

    private SelectedCitiesDao selectedCitiesDao;
    private LiveData<List<SelectedCities>> selectedCitiesLiveData;

    public SelectedCitiesRepository(Application application) {

        SelectedCitiesDatabase database = SelectedCitiesDatabase.getInstance(application);
        selectedCitiesDao = database.selectedCitiesDao();
        selectedCitiesLiveData = selectedCitiesDao.getSelectedCities();
    }

    public void insert(SelectedCities selectedCities) {
        new InsertNoteAsyncTask(selectedCitiesDao).execute(selectedCities);
    }

    public void delete(SelectedCities selectedCities) {
        new DeleteNoteAsyncTask(selectedCitiesDao).execute(selectedCities);

    }

    public void deleteAllRequestToken() {
        new DeleteAllRequestTokenAsyncTask(selectedCitiesDao).execute();
    }

    public LiveData<List<SelectedCities>> getSelectedCitiesLiveData() {

        return selectedCitiesLiveData;
    }



    private static class InsertNoteAsyncTask extends AsyncTask<SelectedCities, Void, Void> {
        private SelectedCitiesDao selectedCitiesDao;

        private InsertNoteAsyncTask(SelectedCitiesDao selectedCitiesDao) {

            this.selectedCitiesDao = selectedCitiesDao;
        }

        @Override
        protected Void doInBackground(SelectedCities... selectedCities) {
            selectedCitiesDao.insert(selectedCities[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<SelectedCities, Void, Void> {
        private SelectedCitiesDao selectedCitiesDao;

        private DeleteNoteAsyncTask(SelectedCitiesDao selectedCitiesDao) {

            this.selectedCitiesDao = selectedCitiesDao;
        }

        @Override
        protected Void doInBackground(SelectedCities... selectedCities) {
            selectedCitiesDao.delete(selectedCities[0].getSelected_city_name());
            return null;
        }
    }

    private static class DeleteAllRequestTokenAsyncTask extends AsyncTask<Void, Void, Void> {
        private SelectedCitiesDao selectedCitiesDao;

        private DeleteAllRequestTokenAsyncTask(SelectedCitiesDao selectedCitiesDao) {

            this.selectedCitiesDao = selectedCitiesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            selectedCitiesDao.deleteAllRequestToken();
            return null;
        }
    }
}

package com.example.mobilexhavadurumu.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.mobilexhavadurumu.models.SelectedCities;
import com.example.mobilexhavadurumu.models.SelectedCitiesDao;

@Database(entities = {SelectedCities.class},version=1)
public abstract class SelectedCitiesDatabase extends RoomDatabase {

    private static SelectedCitiesDatabase instance;

    public abstract SelectedCitiesDao selectedCitiesDao();

    public static synchronized SelectedCitiesDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SelectedCitiesDatabase.class, "selected_cities_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

      private SelectedCitiesDao selectedCitiesDao;
      private PopulateDbAsyncTask(SelectedCitiesDatabase db){
          selectedCitiesDao = db.selectedCitiesDao();
      }
        @Override
        protected Void doInBackground(Void... voids) {
            selectedCitiesDao.insert(new SelectedCities("İzmir"));
            return null;
        }
    }
}

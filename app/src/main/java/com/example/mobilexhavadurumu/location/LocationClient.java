package com.example.mobilexhavadurumu.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.mobilexhavadurumu.models.LocationResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationClient {

    private static LocationManager client = null;
    private static Context context;

    public LocationClient(Context context) {
        this.context = context;
        if (client == null) {
            client = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public static LocationResponse getCurrentLocation() {
         LocationResponse locationResponse = new LocationResponse(null, null);


        return locationResponse;
    }

}

package com.example.mobilexhavadurumu.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.mobilexhavadurumu.R;

import java.util.Calendar;

public class Utils {

    private static ProgressDialog mProgress;
    private static ProgressDialog mProgressTemp;

    public static void uyariMesajGetir(Context cntx, String mesg) {
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(cntx).create();
        alertDialog.setMessage(mesg);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "TAMAM",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void StartActivity(Context cntx, Class secondActivity, String type) {
        //yeni activityi acar ve gecerli activity i kapatir...
        Intent intent = new Intent(cntx, secondActivity);
        intent.putExtra("Type_Act", type);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        cntx.startActivity(intent);
    }

    public static void startLoading(final @NonNull Context cntx, final @NonNull String message) {
        mProgress = new ProgressDialog(cntx);
        mProgress.setTitle("Lütfen Bekleyiniz");
        mProgress.setMessage(message);
        mProgress.setCancelable(false);
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
    }

    public static void startLoadingMain(Context cntx) {
        if (mProgressTemp == null) {
            mProgressTemp = new ProgressDialog(cntx);
            mProgressTemp.setCancelable(false);
            mProgressTemp.setCanceledOnTouchOutside(false);
            mProgressTemp.show();
        }
    }

    public static void stopLoadingTemp() {
        if (mProgressTemp != null) {
            mProgressTemp.dismiss();
            mProgressTemp = null;
        }
    }

    public static void stopLoading() {
        if (mProgress != null) {
            mProgress.dismiss();
            mProgress = null;
        }
    }

    public static String getCurrentDay() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                return "Pazar";
            case Calendar.MONDAY:
                return "Pazartesi";
            case Calendar.TUESDAY:
                return "Salı";
            case Calendar.WEDNESDAY:
                return "Çarşamba";
            case Calendar.THURSDAY:
                return "Perşembe";
            case Calendar.FRIDAY:
                return "Cuma";
            case Calendar.SATURDAY:
                return "Cumartesi";
            default:
                return null;
        }
    }

    public static int setWeatherState(String weatherMain) {

        if(weatherMain.equals("Clear")){
           return R.drawable.hclear;
        }
        else if(weatherMain.equals("Clouds")){
            return R.drawable.hclouds;
        }
        else if(weatherMain.equals("Fog")){
            return R.drawable.hfog;
        }
        else if(weatherMain.equals("Rain")){
            return R.drawable.hrain;
        }
        else if(weatherMain.equals("Snow")){
           return R.drawable.hsnow;
        }
        else if(weatherMain.equals("Thunderstorm")){
            return R.drawable.hthunder;
        }
        else if(weatherMain.equals("Extreme")){
            return R.drawable.hthunder;
        }
        else if(weatherMain.equals("Drizzle")){
             return R.drawable.hrain;
        }

        else{
            return R.drawable.hclear;
        }
    }
}

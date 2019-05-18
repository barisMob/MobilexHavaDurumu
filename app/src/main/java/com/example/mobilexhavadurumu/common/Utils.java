package com.example.mobilexhavadurumu.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

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

}

package com.esspl.hemendra.weatherapp.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by BAPI1 on 02-12-2015.
 */
public class DetectNetworkConnectivity {
    private Context context;

    public DetectNetworkConnectivity(Context context) {
        this.context = context;
    }
    public boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            NetworkInfo networkInfos = connectivityManager.getActiveNetworkInfo();
            if (networkInfos != null){
                if (networkInfos.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }
}

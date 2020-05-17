package com.app.mycalculator.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionStatus
{






    public  static  boolean isAvailable(Context mcontext)
    {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            isConnected = true;
        }
        return isConnected;
    }
}

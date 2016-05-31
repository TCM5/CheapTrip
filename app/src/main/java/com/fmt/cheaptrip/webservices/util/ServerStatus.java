package com.fmt.cheaptrip.webservices.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by santostc on 31-05-2016.
 */
public class ServerStatus {

    public static boolean isServerAvailable(Context context) {
        boolean isAvailable = false;

        if (isNetworkAvailable(context)) {
            try {
                URL url = new URL(WSConfig.LOGIN_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.connect();
                isAvailable = connection.getResponseCode() == 200;
            } catch (IOException e) {
                isAvailable = false;
                // TODO LATER
            }
        } else {
            isAvailable = false;
            // TODO LATER
        }
        return false;
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null;
    }

}

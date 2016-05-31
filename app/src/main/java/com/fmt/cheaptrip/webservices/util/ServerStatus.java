package com.fmt.cheaptrip.webservices.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by santostc on 31-05-2016.
 */
public class ServerStatus {

    public static boolean isServerAvailable(Context context) {
        boolean isAvailable = false;

        if (isNetworkAvailable(context)) {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                URL url = new URL(WSConfig.SERVER_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(10000);
                connection.connect();
                isAvailable = connection.getResponseCode() == 200;
            } catch (Exception e) {
                isAvailable = false;
            }
        } else {
            isAvailable = false;
        }
        return isAvailable;
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null;
    }

}

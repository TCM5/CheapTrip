package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by santostc on 26-05-2016.
 */
public class GplusLoginUtils extends LoginUtils {


    public final static String SP_LOGIN_TYPE_ID = "signed_logintype_gplus";

    /**
     * This method checks if the current user is logged with an google + account.
     * <p/>
     * NOTE: Gplus api should have a better way to check this, but for now is unknown
     *
     * @param context
     * @return
     */
    public static boolean isLogged(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getBoolean("signed_logintype_gplus", false) == true;
    }

    /**
     * @param context
     */
    public static void login(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("signed_logintype_gplus", true);

        editor.commit();
    }


    public static void revoke(Context context) {

        //Revoke, no time for that :(

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, false);

        editor.commit();

    }

    public static void addUserEmail(Context context, String email) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("current_user_email", email);

        editor.commit();
    }

    public static String getUserEmail(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getString("current_user_email", "");
    }

    public static void addUserName(Context context, String name) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("user_name", name);

        editor.commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getString("user_name", "");
    }

    public static void addUserPic(Context context, Uri pic) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("pic", pic.toString());
        editor.commit();
    }

    public static Bitmap getUserPic(Context context) {
        final Bitmap[] result = new Bitmap[1];
        final String pic = PreferenceManager.getDefaultSharedPreferences(context).getString("pic", "");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(pic);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();

                    result[0] = BitmapFactory.decodeStream(input);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        return result[0];
    }

}

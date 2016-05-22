package com.fmt.cheaptrip.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fmt.cheaptrip.activities.LoginActivity;

/**
 * Created by santostc on 22-05-2016.
 */
public class LoginUtils {

    public enum LoginType {GPLUS, FACEBOOK, EMAIL}

    private static LoginUtils instance;

    private LoginUtils() {

    }

    public static LoginUtils getInstance() {
        return instance == null ? instance = new LoginUtils() : instance;
    }

    /**
     * @param loginType
     */
    public void addSignedLoginType(Context context, LoginType loginType) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (loginType) {
            case GPLUS:
                editor.putBoolean("signed_logintype_gplus", true);
                break;
            case FACEBOOK:
                editor.putBoolean("signed_logintype_facebook", true);
                break;
            case EMAIL:
                editor.putBoolean("signed_logintype_email", true);
                break;
        }

        editor.commit();
    }

    public void removeSignedLoginType(Context context, LoginType loginType) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        switch (loginType) {
            case GPLUS:
                editor.putBoolean("signed_logintype_gplus", false);
                break;
            case FACEBOOK:
                editor.putBoolean("signed_logintype_facebook", false);
                break;
            case EMAIL:
                editor.putBoolean("signed_logintype_email", false);
                break;
        }


        editor.commit();
    }


    public <T> boolean isSigned(Context context) {

        LoginActivity loginActivity = null;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);


        if (sharedPref.getBoolean("signed_logintype_gplus", false) == true) {
            return true;
        }

        if (sharedPref.getBoolean("signed_logintype_facebook", false) == true) {
            return true;
        }

        if (sharedPref.getBoolean("signed_logintype_email", false) == true) {
            return true;
        }

        return false;
    }


}

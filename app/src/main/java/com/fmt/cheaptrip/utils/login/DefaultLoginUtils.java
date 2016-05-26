package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by santostc on 26-05-2016.
 */
public class DefaultLoginUtils extends LoginUtils {

    private final static String SP_LOGIN_TYPE_ID = "signed_logintype_default";

    public static boolean isLogged(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getBoolean(SP_LOGIN_TYPE_ID, false) == true;
    }

    /**
     *
     * @param context
     */
    public static void login(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, true);

        editor.commit();
    }


}

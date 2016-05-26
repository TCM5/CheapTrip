package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Created by santostc on 26-05-2016.
 */
public class LoginUtils {

    private final static String SP_LOGIN_CURRENT_USERID_ID = "current_userid_id";
    private final static String SP_LOGIN_CURRENT_USER_EMAIL_ID = "current_user_email";

    /**
     * @param context
     * @return
     */
    public static String getCurrentUserEmail(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getString(SP_LOGIN_CURRENT_USER_EMAIL_ID, "");
    }

    /**
     * @param context
     * @param email
     */
    public static void addCurrentUserEmail(Context context, String email) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("SP_LOGIN_CURRENT_USER_EMAIL_ID", email);

        editor.commit();
    }

    /**
     * @param context
     */
    public static void removeCurrentUserEmail(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("SP_LOGIN_CURRENT_USER_EMAIL_ID", "");

        editor.commit();
    }

    /**
     * @param context
     * @return
     */
    @NonNull
    public static Integer getCurrentUserId(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getInt(SP_LOGIN_CURRENT_USERID_ID, 0);
    }

    /**
     * @param context
     */
    public static void addCurrentUserId(Context context, Integer currentUserId) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(SP_LOGIN_CURRENT_USERID_ID, currentUserId);

        editor.commit();
    }

}

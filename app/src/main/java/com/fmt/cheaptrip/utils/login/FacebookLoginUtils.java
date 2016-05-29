package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by santostc on 26-05-2016.
 */
public class FacebookLoginUtils extends LoginUtils {

    public final static String SP_LOGIN_TYPE_ID = "signed_logintype_facebook";

    private static void initializeFacebookSdk(Context context) {
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(context);
        }
    }

    public static boolean isLogged(Context context) {
        initializeFacebookSdk(context);

        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        return currentAccessToken != null;
    }

    public static Profile getUserProfile(Context context) {
        initializeFacebookSdk(context);

        return Profile.getCurrentProfile();
    }


    public static void revoke(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, false);

        editor.commit();

    }
}

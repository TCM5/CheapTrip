package com.fmt.cheaptrip.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fmt.cheaptrip.activities.LoginActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

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

    public void removeSignedLoginByType(Context context, LoginType loginType) {
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

    /**
     * @param context
     */
    public void removeSignedLogin(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("signed_logintype_gplus", false);
        editor.putBoolean("signed_logintype_facebook", false);
        editor.putBoolean("signed_logintype_email", false);

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


    public void revokeGplusAccount(Context context) {

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile().requestEmail().requestScopes(Plus.SCOPE_PLUS_LOGIN, Plus.SCOPE_PLUS_PROFILE, new Scope("https://www.googleapis.com/auth/plus.profile.emails.read"))
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Plus.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        googleApiClient.connect();

        PendingResult result = Auth.GoogleSignInApi.revokeAccess(googleApiClient);

    }

}

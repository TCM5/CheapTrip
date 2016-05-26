package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

/**
 * Created by santostc on 26-05-2016.
 */
public class GplusLoginUtils extends LoginUtils {

    /**
     * This method checks if the current user is logged with an google + account.
     * <p>
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
     *
     * @param context
     */
    public static void login(Context context){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("signed_logintype_gplus", true);

        editor.commit();
    }

    /**
     *
     * @param context
     */
    public static void revokeAccount(Context context) {

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile().requestEmail().requestScopes(Plus.SCOPE_PLUS_LOGIN, Plus.SCOPE_PLUS_PROFILE, new Scope("https://www.googleapis.com/auth/plus.profile.emails.read"))
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Plus.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        googleApiClient.connect();

        PendingResult result = Auth.GoogleSignInApi.revokeAccess(googleApiClient);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("signed_logintype_gplus", false);

    }

}

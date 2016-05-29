package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

/**
 * Created by santostc on 26-05-2016.
 */
public class FacebookLoginUtils extends LoginUtils {

    public final static String SP_LOGIN_TYPE_ID = "signed_logintype_facebook";

    private static AccessTokenTracker mAccessTokenTracker;


    private static void initializeFacebookSdk(Context context) {
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(context);
        }
    }

    public static boolean isLogged(Context context) {
        initializeFacebookSdk(context);

        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();

        //return currentAccessToken != null;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPref.getBoolean(SP_LOGIN_TYPE_ID, false) == true;
    }

    public static Profile getUserProfile(Context context) {
        initializeFacebookSdk(context);

        Profile profile = Profile.getCurrentProfile();
        return profile;
    }

    public static Bitmap getCurrentUserProfile(Context context) {
        int witdh_heightDPs = 160;
        int witdh_heighPXs = witdh_heightDPs * ((int) context.getResources().getDisplayMetrics().density);

        Uri uri = getUserProfile(context).getProfilePictureUri(witdh_heighPXs, witdh_heighPXs);
        Bitmap userProfileImage = null;

        try {
            userProfileImage = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        } catch (IOException e) {
            // TODO set default image;
        }
        return userProfileImage;
    }

    public static String getCurrentUserName(Context context) {
        return getUserProfile(context).getName();
    }

    @Deprecated
    public static String getCurrentUserEmail(Context context) {
        return null;
    }

    public static void login(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, true);

        editor.commit();
    }

    public static void logout(Context context) {
        revoke(context);
        LoginManager.getInstance().logOut();
    }

    public static void revoke(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, false);

        editor.commit();
    }

    public static void fetchProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // this is where you should have the profile
                        Log.v("fetched info", object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }
}

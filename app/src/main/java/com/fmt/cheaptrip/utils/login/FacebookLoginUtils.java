package com.fmt.cheaptrip.utils.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.io.IOException;
import java.net.URI;

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


    public static void revoke(Context context) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean(SP_LOGIN_TYPE_ID, false);

        editor.commit();

    }
}

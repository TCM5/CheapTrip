package com.fmt.cheaptrip.utils.login;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

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


    public static Bitmap getCurrentUserProfile(final Context context) {


        final String pic = PreferenceManager.getDefaultSharedPreferences(context).getString("pic", "");
        final Bitmap[] bitmaps = new Bitmap[1];

        Picasso.with(context).load(pic).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bitmaps[0] = bitmap;
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        return bitmaps[0];
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

    private static void addPic(String url_str, Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("pic", url_str);
        editor.commit();
    }

    public static void fetchProfile(final Context context) {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                if (data.has("email")) {
                                    String email = data.getString("email");
                                    FacebookLoginUtils.addUserEmail(context, email);
                                }

                                if (data.has("first_name")) {
                                    String firstName = data.getString("first_name");
                                    String lastName = data.getString("last_name");

                                    FacebookLoginUtils.addUserName(context, firstName + " " + lastName);
                                }

                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    FacebookLoginUtils.addPic(profilePicUrl, context);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }


    @Deprecated
    public static Profile getUserProfile(Context context) {
        initializeFacebookSdk(context);

        Profile profile = Profile.getCurrentProfile();
        return profile;
    }
}


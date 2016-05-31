package com.fmt.cheaptrip.utils.login;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public static Profile getUserProfile(Context context) {
        initializeFacebookSdk(context);

        Profile profile = Profile.getCurrentProfile();
        return profile;
    }

    @Deprecated
    public static Bitmap getCurrentUserProfile(Context context) {
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

                    result[0]= BitmapFactory.decodeStream(input);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        return result[0];

    }

    public static Bitmap getCurrentUserProfile1(Context context) {

        String pic = PreferenceManager.getDefaultSharedPreferences(context).getString("pic", "");

        byte[] decodedByte = Base64.decode(pic, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
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

    public static void addPic(String url_str, Context context) {

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("pic", url_str);
        editor.commit();


 /*       try {
            URL url = new URL(url_str);
            InputStream in = url.openConnection().getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putString("pic", imageEncoded);
            editor.commit();


        } catch (IOException e) {
            e.printStackTrace();
        }
*/


    }

    public static void addProfilePic(Uri uri, Context context) {
     /*   Bitmap userProfileImage = null;

        try {
            ContentResolver cr = context.getContentResolver();
            InputStream in = cr.openInputStream(uri);
            userProfileImage = BitmapFactory.decodeStream(in, null, null);

        } catch (IOException e) {
            // TODO set default image;
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userProfileImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("pic", imageEncoded);
        editor.commit();
*/

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
        parameters.putString("fields", "id,email,gender,cover,picture.type(large)"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }
}


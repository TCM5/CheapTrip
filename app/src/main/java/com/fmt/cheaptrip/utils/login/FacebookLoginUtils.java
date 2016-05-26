package com.fmt.cheaptrip.utils.login;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by santostc on 26-05-2016.
 */
public class FacebookLoginUtils extends LoginUtils {

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

    public static boolean revokeAccount(){
        //TODOS
        return true;
    }

}

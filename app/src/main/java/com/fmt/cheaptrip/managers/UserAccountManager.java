package com.fmt.cheaptrip.managers;

import android.content.Context;

import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.utils.login.FacebookLoginUtils;
import com.fmt.cheaptrip.utils.login.GplusLoginUtils;
import com.fmt.cheaptrip.utils.login.LoginUtils;

/**
 * Created by santostc on 26-05-2016.
 */
public class UserAccountManager {

    /**
     * This method checks if the user is logged in any of these login types<br>
     *
     * @param context
     * @return true if is logged
     */
    public static boolean isLogged(Context context) {

        if (FacebookLoginUtils.isLogged(context)) {
            return true;
        } else if (GplusLoginUtils.isLogged(context)) {
            return true;

        } else if (DefaultLoginUtils.isLogged(context)) {
            return true;
        }
        return false;
    }

    /**
     * @param context
     * @return
     */
    public static Integer getCurrentUserId(Context context) {
        return LoginUtils.getCurrentUserId(context);
    }

    public static void clearAllData(Context context) {
        LoginUtils.removeCurrentUserEmail(context);
        logout(context);


    }

    public static void logout(Context context) {

        if (DefaultLoginUtils.isLogged(context)) {
            DefaultLoginUtils.revoke(context);
        } else if (GplusLoginUtils.isLogged(context)) {
            GplusLoginUtils.revoke(context);
        } else if (FacebookLoginUtils.isLogged(context)) {
            FacebookLoginUtils.revoke(context);
        }


    }

}

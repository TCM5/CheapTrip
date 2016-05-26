package com.fmt.cheaptrip.managers;

import android.content.Context;

import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.utils.login.FacebookLoginUtils;
import com.fmt.cheaptrip.utils.login.GplusLoginUtils;

/**
 * Created by santostc on 26-05-2016.
 */
public class LoginManager {

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
}

package com.fmt.cheaptrip.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

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
    public static String getCurrentUserId(Context context) {
        return LoginUtils.getCurrentUserId(context);
    }

    public static Bitmap getCurrentUserProfileImage(Context context) {

        Bitmap userProfilePicture = null;

        if (DefaultLoginUtils.isLogged(context)) {
            // DefaultLoginUtils.getCurrentUserName(context);
        } else if (GplusLoginUtils.isLogged(context)) {
            //  GplusLoginUtils.getCurrentUserName(context);
        } else if (FacebookLoginUtils.isLogged(context)) {
            userProfilePicture = FacebookLoginUtils.getCurrentUserProfile(context);
        }
        if (userProfilePicture != null) {
            return roundShape(userProfilePicture);
        }

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        userProfilePicture = Bitmap.createBitmap(160, 160, conf);
        return roundShape(userProfilePicture);
    }

    public static String getCurrentUserName(Context context) {
        String userName = "N/A";

        if (DefaultLoginUtils.isLogged(context)) {
            //  userName =   DefaultLoginUtils.getCurrentUserName(context);
        } else if (GplusLoginUtils.isLogged(context)) {
            // userName =  GplusLoginUtils.getCurrentUserName(context);
        } else if (FacebookLoginUtils.isLogged(context)) {
            //            userName = FacebookLoginUtils.getCurrentUserName(context);
        }

        return userName;
    }

    public static String getCurrentUserEmail(Context context) {
        return LoginUtils.getCurrentUserEmail(context);
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

    private static Bitmap roundShape(Bitmap bitmap) {
        int targetWidth = 50;
        int targetHeight = 50;

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = bitmap;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

}

package com.fmt.cheaptrip.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fmt.cheaptrip.R;

/**
 * Created by santostc on 16-05-2016.
 */
public class ActivityUtils {

    private static ActivityUtils instance;

    private ActivityUtils() {

    }

    /**
     * @return
     */
    public ActivityUtils getInstance() {

        return instance == null ? instance = new ActivityUtils() : instance;
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment newFragment, int fragmentLayoutId) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentLayoutId, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

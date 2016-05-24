package com.fmt.cheaptrip.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by santostc on 16-05-2016.
 */
public class ActivityUtils {

    private static ActivityUtils instance;

    private ActivityUtils() {
        //DEFAULT
    }

    /**
     * @return
     */
    public ActivityUtils getInstance() {

        return instance == null ? instance = new ActivityUtils() : instance;
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment newFragment, int fragmentLayoutId, boolean addToStack) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentLayoutId, newFragment);


        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
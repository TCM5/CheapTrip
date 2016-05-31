package com.fmt.cheaptrip.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.webservices.util.ServerStatus;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.logging.Logger;

/**
 * @author tiagoCMS
 *         <p/>
 *         This activity ... TODO
 */
public class LoadingActivity extends AppCompatActivity {

    private Logger log = Logger.getLogger(this.getClass().getName());

    private final long LOADING_TIME = 10;
    private int loadingStatus = 0;

    private ProgressBar activity_loading_pb;

    private Handler loadingHandler = new Handler();

    private GoogleApiClient client;

    public LoadingActivity() {
        //nothing to do here
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading);

        activity_loading_pb = (ProgressBar) findViewById(R.id.activity_loading_pb);

        Thread thread = new Thread() {

            @Override
            public void run() {

                while (loadingStatus < 100) {
                    loadingStatus += 1;

                    loadingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            activity_loading_pb.setProgress(loadingStatus);
                        }

                    });

                    try {
                        synchronized (this) {
                            sleep(LOADING_TIME);
                        }

                    } catch (InterruptedException ex) {
                        log.log(null, "TODO");
                    }
                }

                if (isFirstTime()) {
                    redirectToIntroduction();

                } else {

                    if (UserAccountManager.isLogged(getApplicationContext())) {
                        redirectToMain();
                    } else {
                        redirectToLogin();
                    }
                }
            }

        };

        if(!ServerStatus.isServerAvailable(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), R.string.no_server_available, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            thread.start();
        }
    }

    /**
     *
     */
    public void redirectToMain() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    public void redirectToLogin() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    public void redirectToIntroduction() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), IntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    /**
     * This method only check in the shared preferences if is the first time that user enters in the application<br>
     *
     * @return boolean
     */
    private boolean isFirstTime() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        return sharedPref.getBoolean("signed_firsttime", true);
    }

}

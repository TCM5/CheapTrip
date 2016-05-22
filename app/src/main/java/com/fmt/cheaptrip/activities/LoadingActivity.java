package com.fmt.cheaptrip.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.utils.LoginUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.logging.Logger;

/**
 * @author tiagoCMS
 *         <p/>
 *         This activity ... TODO
 */
public class LoadingActivity extends AppCompatActivity {

    private Logger log = Logger.getLogger(this.getClass().getName());

    private final long LOADING_TIME = 100;
    private int loadingStatus = 0;

    private ProgressBar activity_loading_pb;

    private Handler loadingHandler = new Handler();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
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

                if (LoginUtils.getInstance().isSigned(getApplicationContext())) {
                    redirectToMain();
                } else {
                    redirectToLogin();
                }

            }

        };
        thread.start();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


}

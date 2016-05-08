package com.fmt.cheaptrip.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fmt.cheaptrip.R;

import java.util.logging.Logger;

/**
 * @author tiagoCMS
 *
 * This activity ... TODO
 */
public class LoadingActivity extends AppCompatActivity{

    private Logger log = Logger.getLogger(this.getClass().getName());

    private final long LOADING_TIME = 3000;

    public LoadingActivity() {
        //nothing to do here
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_view);

        Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    synchronized (this){
                        sleep(LOADING_TIME);
                    }

                }
                catch (InterruptedException ex){
                     log.log(null, "TODO");
                } finally {
                    Intent intent = new Intent();
                    intent.setClass(getBaseContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getBaseContext().startActivity(intent);
                }

            }
        };

        //starting the loading screen
        thread.start();

    }
}

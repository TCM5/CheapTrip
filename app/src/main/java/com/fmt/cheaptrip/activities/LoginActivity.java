package com.fmt.cheaptrip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.fmt.cheaptrip.fragments.FacebookFragment;
import com.fmt.cheaptrip.fragments.GooglePlusFragment;
import com.fmt.cheaptrip.R;


public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    Button temporaryButton;

    FacebookFragment facebookFragment;
    GooglePlusFragment googlePlusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        if (savedInstanceState != null) {


            facebookFragment = new FacebookFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, facebookFragment).commit();

            // GooglePlusFragment googlePlusFragment = new GooglePlusFragment();
        } else {

            facebookFragment = (FacebookFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

        googlePlusFragment = new GooglePlusFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_fragment_container, googlePlusFragment).commit();


        setContentView(R.layout.activity_login);

        temporaryButton = (Button) findViewById(R.id.temp_button);
        temporaryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToMain();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO

    }

    public void redirectToMain() {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(i);
    }
}


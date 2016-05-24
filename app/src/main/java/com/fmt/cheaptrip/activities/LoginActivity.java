package com.fmt.cheaptrip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.fmt.cheaptrip.Fragments.FacebookFragment;
import com.fmt.cheaptrip.Fragments.GooglePlusFragment;
import com.fmt.cheaptrip.R;


public class LoginActivity extends AppCompatActivity {



    private CallbackManager callbackManager;

    private FacebookFragment facebookFragment;
    private GooglePlusFragment googlePlusFragment;

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


    }

    

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO

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



}


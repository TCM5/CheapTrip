package com.fmt.cheaptrip.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.Fragments.FacebookFragment;


public class LoginActivity extends AppCompatActivity  {

    private CallbackManager callbackManager;

    Button temporaryButton;

    FacebookFragment facebookFragment;
    //GooglePlusFragment googlePlusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

            if (savedInstanceState != null) {


                facebookFragment  = new FacebookFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, facebookFragment).commit();

           // GooglePlusFragment googlePlusFragment = new GooglePlusFragment();
            } else {

            facebookFragment = (FacebookFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }


        setContentView(R.layout.activity_login);

        temporaryButton =  (Button) findViewById(R.id.temp_button);
        temporaryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO

    }
}


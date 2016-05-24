package com.fmt.cheaptrip.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.fragments.login.DefaultLoginFragment;
import com.fmt.cheaptrip.fragments.login.FacebookLoginFragment;
import com.fmt.cheaptrip.fragments.login.GooglePlusLoginFragment;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.ws.TripWSInvoker;


public class LoginActivity extends AppCompatActivity {


    private CallbackManager callbackManager;

    private DefaultLoginFragment defaultLoginFragment;
    private FacebookLoginFragment facebookLoginFragment;
    private GooglePlusLoginFragment googlePlusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        defaultLoginFragment = new DefaultLoginFragment();

        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        transaction.add(R.id.activity_login_default_fragment_container, defaultLoginFragment);
        transaction.commit();


       /* if (savedInstanceState == null) {

            facebookLoginFragment = new FacebookLoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, facebookLoginFragment).commit();

        } else {

            facebookLoginFragment = (FacebookLoginFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

        googlePlusFragment = new GooglePlusLoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_fragment_container, googlePlusFragment).commit();

*/
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

/*
    <com.facebook.login.widget.LoginButton
    android:id="@+id/login_button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal|bottom"
    android:layout_marginBottom="10dp"

    android:paddingBottom="15sp"
    android:paddingTop="15sp"
    android:textSize="15sp" />

    <com.google.android.gms.common.SignInButton
    android:id="@+id/btn_sign_in"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />*/

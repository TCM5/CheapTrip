package com.fmt.cheaptrip.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.fmt.cheaptrip.fragments.login.DefaultLoginFragment;
import com.fmt.cheaptrip.fragments.login.FacebookLoginFragment;
import com.fmt.cheaptrip.fragments.login.GooglePlusLoginFragment;
import com.fmt.cheaptrip.R;


public class LoginActivity extends AppCompatActivity {

    private DefaultLoginFragment defaultLoginFragment;
    private FacebookLoginFragment facebookLoginFragment;
    private GooglePlusLoginFragment googlePlusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            defaultLoginFragment = new DefaultLoginFragment();
            facebookLoginFragment = new FacebookLoginFragment();
            googlePlusFragment = new GooglePlusLoginFragment();

            transaction.add(R.id.activity_login_default_fragment_container, defaultLoginFragment);
            transaction.add(R.id.activity_login_facebook_fragment_container, facebookLoginFragment);
            transaction.add(R.id.activity_login_gplus_fragment_container, googlePlusFragment);

            transaction.commit();

        } else {

            defaultLoginFragment = (DefaultLoginFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
            facebookLoginFragment = (FacebookLoginFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
            googlePlusFragment = (GooglePlusLoginFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

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

package com.fmt.cheaptrip.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.fragments.FacebookFragment;
import com.fmt.cheaptrip.fragments.GooglePlusFragment;

import static android.Manifest.permission.READ_CONTACTS;


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


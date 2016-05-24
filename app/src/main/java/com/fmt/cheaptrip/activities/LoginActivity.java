package com.fmt.cheaptrip.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.fragments.login.FacebookLoginFragment;
import com.fmt.cheaptrip.fragments.login.GooglePlusLoginFragment;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.ws.TripWSInvoker;


public class LoginActivity extends AppCompatActivity {


    private CallbackManager callbackManager;

    private Button loginButton;
    private EditText txtLoginEmail = null;
    private EditText txtLoginPassword = null;

    private FacebookLoginFragment facebookLoginFragment;
    private GooglePlusLoginFragment googlePlusFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

            facebookLoginFragment = new FacebookLoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.login_fragment_container, facebookLoginFragment).commit();

        } else {

            facebookLoginFragment = (FacebookLoginFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

        googlePlusFragment = new GooglePlusLoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_fragment_container, googlePlusFragment).commit();


        setContentView(R.layout.activity_login);

        initializeUIComponents(getBaseContext());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO

    }

    private void initializeUIComponents(final Context context) {
        txtLoginEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);

        loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setEmail(txtLoginEmail.getText().toString());
                user.setPassword(txtLoginPassword.getText().toString());

                TripWSInvoker.login(context, user);
            }
        });
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


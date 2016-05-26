package com.fmt.cheaptrip.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.MainActivity;

/**
 * This class controls the facebook login feature.
 * Here are obtained and stored the values from the facebook login feature
 */
public class FacebookLoginFragment extends Fragment {

    private CallbackManager callbackManager;

    private LoginButton loginButton;

    /**
     * Fragment requires a default constructor and should be the only constructor here.
     */
    public FacebookLoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        View view = inflater.inflate(R.layout.fragment_login_facebook, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);

            }

            @Override
            public void onCancel() {
                System.out.println("CANCELOU");

            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("ERRO");

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

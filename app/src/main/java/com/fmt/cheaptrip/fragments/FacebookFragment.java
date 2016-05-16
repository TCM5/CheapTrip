package com.fmt.cheaptrip.Fragments;

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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.fmt.cheaptrip.R;

/**
 * This class controlls the facebook login and is build as a fragment.
 * Here are obtained and stored the values from the facebook login feature
 *
 */
public class FacebookFragment extends Fragment {



    private CallbackManager callbackManager;

    private LoginButton loginButton;

    /**
     *  Fragment requires a default constructor and should be the only constructor here.
     */
    public FacebookFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        Log.d("TESTE", "TESTE");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view = inflater.inflate(R.layout.activity_login, container, false);

        loginButton = (LoginButton)view.findViewById(R.id.login_button);
        loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    System.out.println("SUCESSO");

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

        System.out.println("callbackManager on ACt");
        //TODO
     }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        //TODO
    }


}

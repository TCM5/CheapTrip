package com.fmt.cheaptrip.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class GooglePlusFragment extends Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RESULT_OK = 0;
    private GoogleApiClient googleApiClient;
    private SignInButton googlePlusButton;

    private static final int RC_SIGN_IN = 0;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult = new ConnectionResult(ConnectionResult.SUCCESS);
    private boolean mSignInClicked;

    public GooglePlusFragment() {
        //

    }

    public static GooglePlusFragment newInstance() {

        GooglePlusFragment fragment = new GooglePlusFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API,Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_login, container, false);

        googlePlusButton = (SignInButton) view.findViewById(R.id.btn_sign_in);

        googlePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGplus();
            }
        });

        return view;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mSignInClicked = false;
        Toast.makeText(getActivity(), "User is connected!", Toast.LENGTH_LONG).show();


        // Get user's information
        //  getProfileInformation();


    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {

            GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), connectionResult.getErrorCode(),
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = connectionResult;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }

    }

    /**
     * Sign-in into google
     */
    private void signInWithGplus() {
        if (!googleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }

        Intent i = new Intent();
        i.setClass(getActivity(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(i);
    }


    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {

                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(getActivity(), RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {

                mIntentInProgress = false;
                googleApiClient.connect();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!googleApiClient.isConnecting()) {
                googleApiClient.connect();
            }
        }
    }

    @Override
    public void onClick(View v) {


    }
}

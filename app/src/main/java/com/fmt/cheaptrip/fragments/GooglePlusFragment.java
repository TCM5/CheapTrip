package com.fmt.cheaptrip.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.LoginActivity;
import com.fmt.cheaptrip.utils.LoginUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;


public class GooglePlusFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String GOOGLE_NAME = "Google";

    private static final int RC_SIGN_IN = 0;
    private static final int RC_SIGN_OUT = -1;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    private SignInButton googlePlusButton;

    private LoginUtils loginUtils = LoginUtils.getInstance();


    public GooglePlusFragment() {
        // Default construcor
    }

    public static GooglePlusFragment newInstance() {

        GooglePlusFragment fragment = new GooglePlusFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile().requestEmail().requestScopes(Plus.SCOPE_PLUS_LOGIN, Plus.SCOPE_PLUS_PROFILE, new Scope("https://www.googleapis.com/auth/plus.profile.emails.read"))
                .requestScopes(new Scope(Scopes.PLUS_LOGIN)).build();

        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).enableAutoManage(getActivity(), this).build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_login, container, false);

        googlePlusButton = (SignInButton) view.findViewById(R.id.btn_sign_in);

        googlePlusButton.setSize(SignInButton.SIZE_STANDARD);
        googlePlusButton.setScopes(googleSignInOptions.getScopeArray());

        googlePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                googleApiClient.connect();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        setLoginButtonText();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount gplusAccount = result.getSignInAccount();
                //  gplusAccount.getDisplayName();
                // gplusAccount.getEmail();

                loginUtils.addSignedLoginType(getActivity(), LoginUtils.LoginType.GPLUS);
                Toast.makeText(getActivity(), gplusAccount.getEmail() + gplusAccount.getDisplayName(), Toast.LENGTH_LONG).show();


                redirectToMain();
            }
        } else if (requestCode == RC_SIGN_OUT) {
            PendingResult result = Auth.GoogleSignInApi.signOut(googleApiClient);
            //TODO
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(getActivity(), "Connected", Toast.LENGTH_LONG).show();
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
    }


    public void redirectToMain() {

        ((LoginActivity) getActivity()).redirectToMain();
    }


    private void setLoginButtonText() {
        for (int i = 0; i < googlePlusButton.getChildCount(); i++) {
            View v = googlePlusButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(getString(R.string.login_btn_text) + " " + GOOGLE_NAME);
                return;
            }
        }
    }
}

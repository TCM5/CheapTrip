package com.fmt.cheaptrip.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.utils.login.FacebookLoginUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import org.json.JSONObject;

/**
 * This class controls the facebook login feature.
 * Here are obtained and stored the values from the facebook login feature
 */
public class FacebookLoginFragment extends Fragment {

    private CallbackManager callbackManager;

    private AccessTokenTracker mAccessTokenTracker;

    private LoginButton loginButton;

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

                if (AccessToken.getCurrentAccessToken() != null) {
                    mAccessTokenTracker = new AccessTokenTracker() {
                        @Override
                        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                            mAccessTokenTracker.stopTracking();
                            if (currentAccessToken == null) {

                            } else {
                                FacebookLoginUtils.fetchProfile(getActivity().getApplicationContext());

                                final User user = new User();
                                user.setName(FacebookLoginUtils.getUserName(getActivity().getApplicationContext()));
                                user.setEmail(FacebookLoginUtils.getUserEmail(getActivity().getApplicationContext()));
                                user.setContactNumber("N/A");

                                TripWSInvoker.registerUser(getActivity().getApplicationContext(), user, new WSResponseListener() {
                                    @Override
                                    public void onResponse(WSResponseObject response) {
                                        if ("true".equalsIgnoreCase(response.getSuccess())) {

                                            FacebookLoginUtils.login(getActivity().getApplicationContext());

                                            Intent intent = new Intent();
                                            intent.setClass(getActivity(), MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            getActivity().startActivity(intent);

                                        } else {
                                            Toast.makeText(getActivity().getApplicationContext(), "HERE" + response.getError(), Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onError(VolleyError error) {

                                        // Already registerd
                                        FacebookLoginUtils.login(getActivity().getApplicationContext());

                                        Intent intent = new Intent();
                                        intent.setClass(getActivity(), MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().startActivity(intent);
                                    }
                                });
                            }
                        }
                    };
                    mAccessTokenTracker.startTracking();
                    AccessToken.refreshCurrentAccessTokenAsync();
                } else {
                    //TODO
                }
            }

            @Override
            public void onCancel() {
                //TODO

            }

            @Override
            public void onError(FacebookException e) {
                //TODO

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

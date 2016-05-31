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
import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.utils.login.FacebookLoginUtils;

import org.json.JSONObject;

/**
 * This class controls the facebook login feature.
 * Here are obtained and stored the values from the facebook login feature
 */
public class FacebookLoginFragment extends Fragment {

    private CallbackManager callbackManager;

    private AccessTokenTracker mAccessTokenTracker;

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

/*                Bundle params = new Bundle();
                params.putString("fields", "id,email,gender,cover,picture.type(large)");
                new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                if (response != null) {
                                    try {
                                        JSONObject data = response.getJSONObject();
                                        if (data.has("picture")) {
                                            String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                          //  FacebookLoginUtils.addPic(profilePicUrl, getActivity().getApplicationContext());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).executeAsync();*/

               /* Uri uri = Profile.getCurrentProfile().getProfilePictureUri(200,200);
                FacebookLoginUtils.addProfilePic(uri, getActivity());

                ProfileTracker profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        this.stopTracking();
                        Uri uri = Profile.getCurrentProfile().getProfilePictureUri(200,200);
                        FacebookLoginUtils.addProfilePic(uri, getActivity());
                        Profile.setCurrentProfile(currentProfile);

                    }
                };
                profileTracker.startTracking();

                */

                if (AccessToken.getCurrentAccessToken() != null) {
                    mAccessTokenTracker = new AccessTokenTracker() {
                        @Override
                        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                            mAccessTokenTracker.stopTracking();
                            if (currentAccessToken == null) {

                            } else {
                                FacebookLoginUtils.fetchProfile(getActivity().getApplicationContext());


                                FacebookLoginUtils.login(getActivity().getApplicationContext());

                                Intent intent = new Intent();
                                intent.setClass(getActivity(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().startActivity(intent);
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

package com.fmt.cheaptrip.fragments.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.activities.SignInActivity;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultLoginFragment extends Fragment {

    private Button loginButton;
    private Button signInButton;

    private EditText txtLoginEmail;
    private EditText txtLoginPassword;

    public DefaultLoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_default, container, false);

        txtLoginEmail = (EditText) view.findViewById(R.id.txtLoginEmail);
        txtLoginPassword = (EditText) view.findViewById(R.id.txtLoginPassword);

        loginButton = (Button) view.findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setEmail(txtLoginEmail.getText().toString());
                user.setPassword(txtLoginPassword.getText().toString());

                TripWSInvoker.login(getActivity(), user, new WSResponseListener() {
                    @Override
                    public void onResponse(WSResponseObject response) {
                        if (response.getSuccess().equalsIgnoreCase("true")) {
                            User user = response.getUser();

                            DefaultLoginUtils.login(getActivity().getApplicationContext());
                            DefaultLoginUtils.addUserEmail(getActivity().getApplicationContext(), user.getEmail());
                            DefaultLoginUtils.addUserName(getActivity().getApplicationContext(), user.getName());
                            DefaultLoginUtils.addCurrentUserId(getActivity().getApplicationContext(), String.valueOf(user.getUserId()));

                            Toast.makeText(getContext(), R.string.success_loggin_msg, Toast.LENGTH_SHORT).show();
                            redirectToMain(user);
                        } else {
                            Toast.makeText(getContext(), response.getError(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(error);
                    }
                });
            }
        });

        signInButton = (Button) view.findViewById(R.id.btnSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Sign in called", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(), SignInActivity.class);
                startActivityForResult(intent, 1);

            }
        });

        return view;
    }

    /**
     * This method solves the activity for result called by the Signin button.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {
                String email = data.getStringExtra("email");
                this.txtLoginEmail.setText(email);
                this.txtLoginPassword.setText("");

            } else if (resultCode == Activity.RESULT_CANCELED) {
                //TODO
            }
        }
    }

    private void redirectToMain(User user) {
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("user", user);
        getActivity().startActivity(intent);
    }
}

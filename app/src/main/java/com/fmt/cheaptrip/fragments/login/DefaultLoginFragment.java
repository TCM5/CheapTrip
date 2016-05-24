package com.fmt.cheaptrip.fragments.login;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.ws.TripWSInvoker;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultLoginFragment extends Fragment {

    private Button loginButton;
    private EditText txtLoginEmail;
    private EditText txtLoginPassword;

    public DefaultLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

                TripWSInvoker.login(getActivity(), user);
            }
        });

        return view;
    }

}

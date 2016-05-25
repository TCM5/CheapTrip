package com.fmt.cheaptrip.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Miguel on 25/05/16.
 */

public class SignInFragment extends Fragment {

    private EditText txtName = null;
    private EditText txtContactNumber = null;
    private EditText txtEmail = null;
    private EditText txtPassword = null;

    private Button registerButton = null;


    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        txtName = (EditText) view.findViewById(R.id.txtName);
        txtContactNumber = (EditText) view.findViewById(R.id.txtContactNumber);
        txtEmail = (EditText) view.findViewById(R.id.txtEmail);
        txtPassword = (EditText) view.findViewById(R.id.txtPassword);

        registerButton = (Button) view.findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();

                user.setName(txtName.getText().toString());
                user.setContactNumber(txtContactNumber.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                TripWSInvoker.registerUser(getContext(), user);
            }
        });

        return view;
    }
}

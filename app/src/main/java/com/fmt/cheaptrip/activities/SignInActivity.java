package com.fmt.cheaptrip.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;


public class SignInActivity extends AppCompatActivity {


    private EditText txtName = null;
    private EditText txtContactNumber = null;
    private EditText txtEmail = null;
    private EditText txtPassword = null;

    private Button registerButton = null;


    public SignInActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signin_default);

        txtName = (EditText) findViewById(R.id.txtName);
        txtContactNumber = (EditText) findViewById(R.id.txtContactNumber);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        registerButton = (Button) findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User();

                user.setName(txtName.getText().toString());
                user.setContactNumber(txtContactNumber.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                TripWSInvoker.registerUser(getApplicationContext(), user, new WSResponseListener() {
                    @Override
                    public void onResponse(WSResponseObject response) {
                        if (response.getSuccess().equalsIgnoreCase("true")) {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("email",user.getEmail());
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(error);
                    }
                });

                // validacao do webservices aqui, mas ha-de ser kk cena assim:
                // Aqui fazes um intent de volta para o login, mas levas um resultado. Sucesso ou cancelado.

        /*        if (registerUser == sucesso) {
                    Intent returnIntent = new Intent();
                    //    returnIntent.putExtra("email",email); // com esta linha podemos logo preencher o login na actividade do login :)
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
*/
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}
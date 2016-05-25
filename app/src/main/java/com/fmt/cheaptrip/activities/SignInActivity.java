package com.fmt.cheaptrip.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.ws.TripWSInvoker;


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
                User user = new User();

                user.setName(txtName.getText().toString());
                user.setContactNumber(txtContactNumber.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                TripWSInvoker.registerUser(getApplicationContext(), user);


                // validacao do webservice aqui, mas ha-de ser kk cena assim:
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

                Intent returnIntent = new Intent();
                returnIntent.putExtra("email",user.getEmail()); // com esta linha podemos logo preencher o login na actividade do login :)
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
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
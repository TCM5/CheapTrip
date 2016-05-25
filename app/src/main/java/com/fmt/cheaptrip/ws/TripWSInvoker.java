package com.fmt.cheaptrip.ws;

/**
 * Created by Miguel on 24/05/16.
 */

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.ws.request.CustomStringRequest;
import com.fmt.cheaptrip.ws.util.CustomJSONParser;
import com.fmt.cheaptrip.ws.util.WSConfig;

/**
 * Created by Miguel on 22/05/16.
 */
public class TripWSInvoker {

    public static void registerUser(final Context context, User user) {

        CustomStringRequest newUserRequest = new CustomStringRequest(Request.Method.POST, WSConfig.REGISTER_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Resposta de Sucesso: " + response.toString());
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erro: " + error.getMessage());
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        //Parametros POST necessarios para o Login
        newUserRequest.addParam(WSConfig.PARAM_NAME, user.getName());
        newUserRequest.addParam(WSConfig.PARAM_CONTACT_NUMBER, user.getContactNumber().toString());
        newUserRequest.addParam(WSConfig.PARAM_EMAIL, user.getEmail());
        newUserRequest.addParam(WSConfig.PARAM_PASSWORD, user.getPassword());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(newUserRequest);
    }


    public static void login(final Context context, User user) {
        CustomStringRequest loginRequest = new CustomStringRequest(Request.Method.POST, WSConfig.LOGIN_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println("Resposta de Sucesso - " + response.toString());
                        try {
                            User user = CustomJSONParser.getInstance().stringToObject(response.toString(), User.class);
                            Toast.makeText(context, "Sucesso - " + response.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Sucesso Cast Ex - " + response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erro: " + error.getMessage());
                        Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        //Parametros POST necessarios para o Login
        loginRequest.addParam(WSConfig.PARAM_EMAIL, user.getEmail());
        loginRequest.addParam(WSConfig.PARAM_PASSWORD, user.getPassword());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(loginRequest);
    }
}
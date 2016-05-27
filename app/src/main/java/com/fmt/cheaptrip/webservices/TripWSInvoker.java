package com.fmt.cheaptrip.webservices;

/**
 * Created by Miguel on 24/05/16.
 */

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.entities.Vehicle;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.webservices.request.CustomStringRequest;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;
import com.fmt.cheaptrip.webservices.util.CustomJSONParser;
import com.fmt.cheaptrip.webservices.util.WSConfig;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 22/05/16.
 */
public class TripWSInvoker {

    public static void registerUser(final Context context, User user, final WSResponseListener wsResponse) {

        CustomStringRequest newUserRequest = new CustomStringRequest(Request.Method.POST, WSConfig.REGISTER_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        WSResponseObject responseObject = CustomJSONParser.getInstance().stringToObject(response.toString(), WSResponseObject.class);
                        wsResponse.onResponse(responseObject);
                        /*
                        System.out.println("Resposta de Sucesso: " + response.toString());
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                        */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wsResponse.onError(error);
                        /*
                        System.out.println("Erro: " + error.getMessage());
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                        */
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


    public static void login(final Context context, User user, final WSResponseListener wsResponse) {
        CustomStringRequest loginRequest = new CustomStringRequest(Request.Method.POST, WSConfig.LOGIN_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        WSResponseObject responseObject = CustomJSONParser.getInstance().stringToObject(response.toString(), WSResponseObject.class);
                        wsResponse.onResponse(responseObject);
                        /*
                        System.out.println("Resposta de Sucesso - " + response.toString());
                        try {
                            User user = CustomJSONParser.getInstance().stringToObject(response.toString(), User.class);
                            Toast.makeText(context, "Sucesso - " + response.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(context, "Sucesso Cast Ex - " + response.toString(), Toast.LENGTH_LONG).show();
                        }
                        */
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wsResponse.onError(error);
                        /*
                        System.out.println("Erro: " + error.getMessage());
                        Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        */
                    }
                }
        );

        //Parametros POST necessarios para o Login
        loginRequest.addParam(WSConfig.PARAM_EMAIL, user.getEmail());
        loginRequest.addParam(WSConfig.PARAM_PASSWORD, user.getPassword());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(loginRequest);
    }

    public static void receivedTrips(final Context context, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Type listType = new TypeToken<ArrayList<Trip>>() {}.getType();
                        List<Trip> trips = CustomJSONParser.getInstance().stringToObject(response.toString(), listType);

                        WSResponseObject responseObject = new WSResponseObject();
                        responseObject.setTrips(trips);
                        wsResponse.onResponse(responseObject);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wsResponse.onError(error);
                    }
                }
        );

        String currentUserId = "40";// String.valueOf(UserAccountManager.getCurrentUserId(context.getApplicationContext()));

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_RECEIVED_TRIPS);
        receivedTripsRequest.addParam(WSConfig.PARAM_USERID, currentUserId);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void givenTrips(final Context context, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Type listType = new TypeToken<ArrayList<Trip>>() {}.getType();
                        List<Trip> trips = CustomJSONParser.getInstance().stringToObject(response.toString(), listType);

                        WSResponseObject responseObject = new WSResponseObject();
                        responseObject.setTrips(trips);
                        wsResponse.onResponse(responseObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wsResponse.onError(error);
                    }
                }
        );

        String currentUserId = "6";// String.valueOf(UserAccountManager.getCurrentUserId(context.getApplicationContext()));

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_SHARED_TRIPS);
        receivedTripsRequest.addParam(WSConfig.PARAM_USERID, currentUserId);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void userVehicles(final Context context, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.VEHICLES_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        List<Vehicle> vehicles = CustomJSONParser.getInstance().stringToObject(response.toString(), List.class);

                        WSResponseObject responseObject = new WSResponseObject();
                        responseObject.setVehicles(vehicles);
                        wsResponse.onResponse(responseObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wsResponse.onError(error);
                    }
                }
        );

        String currentUserId = "1";// String.valueOf(UserAccountManager.getCurrentUserId(context.getApplicationContext()));

        receivedTripsRequest.addParam(WSConfig.PARAM_USERID, currentUserId);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }
}
package com.fmt.cheaptrip.webservices;

/**
 * Created by Miguel on 24/05/16.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fmt.cheaptrip.entities.SubscribeTrip;
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
import java.util.Date;
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
                        Type listType = new TypeToken<ArrayList<Trip>>() {
                        }.getType();
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
                        Type listType = new TypeToken<ArrayList<Trip>>(){}.getType();
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

    public static void getUserVehicles(final Context context, Integer userId, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.VEHICLES_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Type listType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
                        List<Vehicle> vehicles = CustomJSONParser.getInstance().stringToObject(response.toString(), listType);

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

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_GET_USER_VEHICLES);
        receivedTripsRequest.addParam(WSConfig.PARAM_USERID, userId.toString());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void searchTrip(final Context context, String originCity, String destinyCity, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Type listType = new TypeToken<ArrayList<Trip>>() {
                        }.getType();
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

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_FIND_TRIPS);
        receivedTripsRequest.addParam(WSConfig.PARAM_START_CITY, originCity);
        receivedTripsRequest.addParam(WSConfig.PARAM_END_CITY, destinyCity);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void registerTrip(final Context context, Trip trip, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        WSResponseObject responseObject = CustomJSONParser.getInstance().stringToObject(response.toString(), WSResponseObject.class);
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

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_REGISTER_TRIP);
        receivedTripsRequest.addParam(WSConfig.PARAM_DRIVER_ID, trip.getDriverId().toString());
        receivedTripsRequest.addParam(WSConfig.PARAM_VEHICLE_ID, trip.getVehicleId().toString());

        receivedTripsRequest.addParam(WSConfig.PARAM_START_CITY, trip.getStartCity());
        receivedTripsRequest.addParam(WSConfig.PARAM_END_CITY, trip.getEndCity());

        receivedTripsRequest.addParam(WSConfig.PARAM_START_POINT, trip.getStartPoint());
        receivedTripsRequest.addParam(WSConfig.PARAM_END_POINT, trip.getEndPoint());

        receivedTripsRequest.addParam(WSConfig.PARAM_DATE, WSConfig.convertDateToString(trip.getTripDate()));
        receivedTripsRequest.addParam(WSConfig.PARAM_TRIP_PRICE, trip.getPrice().toString());
        receivedTripsRequest.addParam(WSConfig.PARAM_TRIP_OBSERVATIONS, trip.getObservations());
        receivedTripsRequest.addParam(WSConfig.PARAM_BAGAGGE_SIZE, trip.getBaggageSize());
        receivedTripsRequest.addParam(WSConfig.PARAM_DELAY_TOLERANCE, trip.getDelayTolerance().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void subscribeTrip(final Context context, SubscribeTrip subscribeTrip, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        WSResponseObject responseObject = CustomJSONParser.getInstance().stringToObject(response.toString(), WSResponseObject.class);
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

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_SUBSCRIBE_TRIP);
        receivedTripsRequest.addParam(WSConfig.PARAM_TRIP_ID, subscribeTrip.getTripId().toString());
        receivedTripsRequest.addParam(WSConfig.PARAM_PASSENGER_ID, subscribeTrip.getPassengerId().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }

    public static void confirmTrip(final Context context, String tripId, final WSResponseListener wsResponse) {

        CustomStringRequest receivedTripsRequest = new CustomStringRequest(Request.Method.POST, WSConfig.TRIPS_URL,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        WSResponseObject responseObject = CustomJSONParser.getInstance().stringToObject(response.toString(), WSResponseObject.class);
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

        String currentUserId = String.valueOf(UserAccountManager.getCurrentUserId(context.getApplicationContext()));

        receivedTripsRequest.addParam(WSConfig.PARAM_ACTION, WSConfig.ACTION_CONFIRM_TRIP);
        receivedTripsRequest.addParam(WSConfig.PARAM_TRIP_ID, tripId);
        receivedTripsRequest.addParam(WSConfig.PARAM_PASSENGER_ID, currentUserId);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(receivedTripsRequest);
    }
}
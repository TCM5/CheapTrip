package com.fmt.cheaptrip.webservices.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Miguel on 24/05/16.
 */

public class WSConfig {

    //URL to our Web Services
    public static final String LOGIN_URL = "http://miguelnobre.com/php/EasyTrip/EasyTripLogin.php";
    public static final String REGISTER_URL = "http://miguelnobre.com/php/EasyTrip/EasyTripRegister.php";
    public static final String TRIPS_URL = "http://miguelnobre.com/php/EasyTrip/Trip.php";
    public static final String VEHICLES_URL = "http://miguelnobre.com/php/EasyTrip/User/Vehicle.php";

    //POST Parameters
    public static final String PARAM_ACTION = "action";

    //User Parameters
    public static final String PARAM_USERID = "userid";
    public static final String PARAM_CONTACT_NUMBER = "contact";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_PASSWORD = "password";

    //Trip Parameters
    public static final String PARAM_TRIP_ID = "tripid";
    public static final String PARAM_DRIVER_ID = "driverid";
    public static final String PARAM_PASSENGER_ID = "passengerid";
    public static final String PARAM_VEHICLE_ID = "vehicleid";
    public static final String PARAM_START_CITY = "startcity";
    public static final String PARAM_END_CITY = "endcity";
    public static final String PARAM_START_POINT = "startpoint";
    public static final String PARAM_END_POINT = "endpoint";
    public static final String PARAM_DATE = "date";
    public static final String PARAM_TRIP_PRICE = "price";
    public static final String PARAM_TRIP_OBSERVATIONS = "observations";
    public static final String PARAM_BAGAGGE_SIZE = "baggagesize";
    public static final String PARAM_DELAY_TOLERANCE = "delaytolerance";

    //TRIPS ACTION
    public static final String ACTION_REGISTER_TRIP = "registerTrip";
    public static final String ACTION_FIND_TRIPS = "findTrips";
    public static final String ACTION_RECEIVED_TRIPS = "myPassengerTrips";
    public static final String ACTION_SHARED_TRIPS = "myDriverTrips";
    public static final String ACTION_SUBSCRIBE_TRIP = "subscribeTrip";
    public static final String ACTION_CONFIRM_TRIP = "confirmTrip";


    public static final String ACTION_GET_USER_VEHICLES = "getUserVehicles";

    // Create the MySQL datetime string
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String convertDateToString(Date date) {
        String dateString = sdf.format(date);
        return dateString;
    }
}

package com.fmt.cheaptrip.webservices.util;

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
    public static final String PARAM_USERID = "userid";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_CONTACT_NUMBER = "contact";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_ACTION = "action";

    //TRIPS ACTION
    public static final String ACTION_FIND_TRIPS = "findTrips";
    public static final String ACTION_RECEIVED_TRIPS = "myPassengerTrips";
    public static final String ACTION_SHARED_TRIPS = "myDriverTrips";
}

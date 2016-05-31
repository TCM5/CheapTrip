package com.fmt.cheaptrip.webservices.response;

import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.entities.Vehicle;

import java.util.List;

/**
 * Created by Miguel on 25/05/16.
 */

public class WSResponseObject {

    private String success;
    private String error;
    private User user;
    private Vehicle vehicle;
    private List<Trip> trips;
    private List<Vehicle> vehicles;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}

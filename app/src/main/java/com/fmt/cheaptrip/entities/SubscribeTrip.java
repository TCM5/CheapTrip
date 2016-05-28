package com.fmt.cheaptrip.entities;

/**
 * Created by Miguel on 28/05/16.
 */

public class SubscribeTrip {

    private Integer tripId;
    private Integer passengerId;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }
}

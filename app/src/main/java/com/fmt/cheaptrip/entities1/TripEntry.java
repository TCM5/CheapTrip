package com.fmt.cheaptrip.entities1;

import android.location.Address;

import java.util.Date;

/**
 * Created by santostc on 16-05-2016.
 */
public class TripEntry {

    private Address originLocation;
    private Address destinyLocation;
    private Date date;
    private String price;

    public TripEntry() {

    }

    public Address getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(Address originLocation) {

        this.originLocation = originLocation;
    }

    public Address getDestinyLocation() {

        return destinyLocation;
    }

    public void setDestinyLocation(Address destinyLocation) {
        this.destinyLocation = destinyLocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.fmt.cheaptrip.Entities;

import android.location.Address;

/**
 * Created by ASUS-TCMS on 15/05/2016.
 */
public class LocationEntry {

    private Address address;

    public LocationEntry(Address address) {
        this.address = address;
    }

    public String getAddress() {
        return address.toString();
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package com.fmt.cheaptrip.entities;

import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ASUS-TCMS on 15/05/2016.
 */
public class LocationEntry {

    private Address address;

    /**
     * @param address
     */
    public LocationEntry(Address address) {
        this.address = address;
    }

    /**
     * @return
     */
    public String getAddress() {

        String display_address = address.getLocality();
        String countryAddress = address.getCountryName();

        //display_address += address.getAddressLine(0) + "\n";
        /*
        for (int i = 1; i < address.getMaxAddressLineIndex(); i++) {
            display_address += address.getAddressLine(i) + ", ";
        }
        */

        //display_address = display_address.substring(0, display_address.length());


        if (display_address == null || display_address.isEmpty() || display_address.equals("null")) {
            display_address = address.getFeatureName();
        }

        return display_address + ", " + countryAddress;
    }

    /**
     * @param address
     */
    public void setAddress(Address address) {

        this.address = address;
    }

    public Double getLatitude() {
        return address.getLatitude();
    }

    public Double getLongitude() {
        return address.getLongitude();
    }

    public String getCity() {
        return address.getLocality();
    }

    /**
     * @return
     */
    public LatLng getLatLng() {
        return new LatLng(getLatitude(), getLongitude());
    }


    /**
     * @return
     */
    public String toString() {
        String display_address = "";

        if (address.getFeatureName() != null) {
            display_address += address + ", ";
        }

        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
            display_address += address.getAddressLine(i);
        }

        return display_address;
    }


}

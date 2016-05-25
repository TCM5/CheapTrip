package com.fmt.cheaptrip.entities;

import java.io.Serializable;

/**
 * Created by Miguel on 24/05/16.
 */

public class User implements Serializable {

    private Integer userId;
    private String name;
    private String contactNumber;
    private String email;
    private String password;
    private Double driverRank;
    private Double passengerRank;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getDriverRank() {
        return driverRank;
    }

    public void setDriverRank(Double driverRank) {
        this.driverRank = driverRank;
    }

    public Double getPassengerRank() {
        return passengerRank;
    }

    public void setPassengerRank(Double passengerRank) {
        this.passengerRank = passengerRank;
    }
}

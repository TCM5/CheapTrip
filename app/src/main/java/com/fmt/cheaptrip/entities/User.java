package com.fmt.cheaptrip.entities;

import java.io.Serializable;

/**
 * Created by Miguel on 24/05/16.
 */

public class User implements Serializable {

    private Integer userId;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private Integer driverRank;
    private Integer passengerRank;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getDriverRank() {
        return driverRank;
    }

    public void setDriverRank(Integer driverRank) {
        this.driverRank = driverRank;
    }

    public Integer getPassengerRank() {
        return passengerRank;
    }

    public void setPassengerRank(Integer passengerRank) {
        this.passengerRank = passengerRank;
    }
}

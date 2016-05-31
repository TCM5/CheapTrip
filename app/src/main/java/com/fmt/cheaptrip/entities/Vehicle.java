package com.fmt.cheaptrip.entities;

/**
 * Created by Miguel on 27/05/16.
 */

public class Vehicle {

    private Integer vehicleId;
    private String brand;
    private String model;
    private String year;
    private int seatsNumber;

    public Vehicle() {

    }

    public Vehicle(String brand, String model, String year, int seatsNumber) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.seatsNumber = seatsNumber;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Override
    public String toString() {
        return this.getBrand() + " " + this.getModel();
    }
}
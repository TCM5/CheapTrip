package com.fmt.cheaptrip.entities;

import java.util.Date;

/**
 * Created by santostc on 26-05-2016.
 */
public class Trip {

    private Integer tripId;
    private Integer driverId;
    private Integer vehicleId;
    private String StartCity;
    private String EndCity;
    private String StartPoint;
    private String EndPoint;
    private Date tripDate;
    private Double Price;
    private String Observations;
    private String BaggageSize;
    private Integer DelayTolerance;
    private Integer Rank;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartCity() {
        return StartCity;
    }

    public void setStartCity(String startCity) {
        StartCity = startCity;
    }

    public String getEndCity() {
        return EndCity;
    }

    public void setEndCity(String endCity) {
        EndCity = endCity;
    }

    public String getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(String startPoint) {
        StartPoint = startPoint;
    }

    public String getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(String endPoint) {
        EndPoint = endPoint;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getObservations() {
        return Observations;
    }

    public void setObservations(String observations) {
        Observations = observations;
    }

    public String getBaggageSize() {
        return BaggageSize;
    }

    public void setBaggageSize(String baggageSize) {
        BaggageSize = baggageSize;
    }

    public Integer getDelayTolerance() {
        return DelayTolerance;
    }

    public void setDelayTolerance(Integer delayTolerance) {
        DelayTolerance = delayTolerance;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }



}

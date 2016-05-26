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
    private Double StartPoint;
    private Double EndPoint;
    private Date date;
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

    public Double getStartPoint() {
        return StartPoint;
    }

    public void setStartPoint(Double startPoint) {
        StartPoint = startPoint;
    }

    public Double getEndPoint() {
        return EndPoint;
    }

    public void setEndPoint(Double endPoint) {
        EndPoint = endPoint;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

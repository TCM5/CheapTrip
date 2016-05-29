package com.fmt.cheaptrip.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by santostc on 26-05-2016.
 */
public class Trip implements Parcelable {

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

    //Contextual Information
    private Date confirmedDate;
    private String vehicleBrand;
    private String vehicleModel;

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

    public Date getConfirmDate() {
        return confirmedDate;
    }

    public void setConfirmDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Trip(){

    }

    protected Trip(Parcel in) {
        tripId = in.readByte() == 0x00 ? null : in.readInt();
        driverId = in.readByte() == 0x00 ? null : in.readInt();
        vehicleId = in.readByte() == 0x00 ? null : in.readInt();
        StartCity = in.readString();
        EndCity = in.readString();
        StartPoint = in.readString();
        EndPoint = in.readString();
        long tmpTripDate = in.readLong();
        tripDate = tmpTripDate != -1 ? new Date(tmpTripDate) : null;
        Price = in.readByte() == 0x00 ? null : in.readDouble();
        Observations = in.readString();
        BaggageSize = in.readString();
        DelayTolerance = in.readByte() == 0x00 ? null : in.readInt();
        Rank = in.readByte() == 0x00 ? null : in.readInt();
        confirmedDate = in.readByte() == 0x00 ? null : new Date(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (tripId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(tripId);
        }
        if (driverId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(driverId);
        }
        if (vehicleId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(vehicleId);
        }
        dest.writeString(StartCity);
        dest.writeString(EndCity);
        dest.writeString(StartPoint);
        dest.writeString(EndPoint);
        dest.writeLong(tripDate != null ? tripDate.getTime() : -1L);
        if (Price == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(Price);
        }
        dest.writeString(Observations);
        dest.writeString(BaggageSize);
        if (DelayTolerance == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(DelayTolerance);
        }
        if (Rank == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(Rank);
        }
        if(confirmedDate == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(confirmedDate.getTime());
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };
}
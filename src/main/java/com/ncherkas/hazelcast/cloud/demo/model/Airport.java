package com.ncherkas.hazelcast.cloud.demo.model;

import java.io.Serializable;

public class Airport implements Serializable {

    private String code;
    private String description;
    private double latitude;
    private double longitude;

    public Airport() {
    }

    public Airport(String code, String description, double latitude, double longitude) {
        this.code = code;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

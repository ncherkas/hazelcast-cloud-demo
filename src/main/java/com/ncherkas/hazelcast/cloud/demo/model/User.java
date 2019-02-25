package com.ncherkas.hazelcast.cloud.demo.model;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String lastCardUsePlace;
    private long lastCardUseTimestamp;

    public User() {
    }

    public User(int userId, String lastCardUsePlace, long lastCardUseTimestamp) {
        this.userId = userId;
        this.lastCardUsePlace = lastCardUsePlace;
        this.lastCardUseTimestamp = lastCardUseTimestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastCardUsePlace() {
        return lastCardUsePlace;
    }

    public void setLastCardUsePlace(String lastCardUsePlace) {
        this.lastCardUsePlace = lastCardUsePlace;
    }

    public long getLastCardUseTimestamp() {
        return lastCardUseTimestamp;
    }

    public void setLastCardUseTimestamp(long lastCardUseTimestamp) {
        this.lastCardUseTimestamp = lastCardUseTimestamp;
    }
}

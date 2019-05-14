package com.ncherkas.hazelcast.cloud.demo.model;

import java.io.Serializable;
import java.time.Instant;

public class User implements Serializable {

    private int userId;
    private String lastCardUsePlace;
    private Instant lastCardUseTimestamp;

    public User() {
    }

    public User(int userId, String lastCardUsePlace, Instant lastCardUseTimestamp) {
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

    public Instant getLastCardUseTimestamp() {
        return lastCardUseTimestamp;
    }

    public void setLastCardUseTimestamp(Instant lastCardUseTimestamp) {
        this.lastCardUseTimestamp = lastCardUseTimestamp;
    }
}

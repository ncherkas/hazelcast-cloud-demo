package com.ncherkas.hazelcast.cloud.demo.model;

import java.time.Instant;

public class ValidateRequest extends Request {

    public static final String TYPE = "VALIDATE";

    private Integer userId;
    private String airportCode;
    private Instant transactionTimestamp;

    public ValidateRequest() {
        super(TYPE);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Instant getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Instant transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}

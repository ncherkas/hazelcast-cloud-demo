package com.ncherkas.hazelcast.cloud.demo.model;

public class ValidateRequest extends Request {

    public static final String TYPE = "VALIDATE";

    private Integer userId;
    private String airportCode;
    private long transactionTimestamp;

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

    public long getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(long transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}

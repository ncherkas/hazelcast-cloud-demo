package com.ncherkas.hazelcast.cloud.demo.model;

public class ValidateResponse extends Response {

    private boolean valid;

    public ValidateResponse() {
    }

    public ValidateResponse(boolean valid, String message) {
        super(message);
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

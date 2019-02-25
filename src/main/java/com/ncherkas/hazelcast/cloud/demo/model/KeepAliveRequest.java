package com.ncherkas.hazelcast.cloud.demo.model;

public class KeepAliveRequest extends Request {

    public static final String TYPE = "KEEP_ALIVE";

    public KeepAliveRequest() {
        super(TYPE);
    }
}

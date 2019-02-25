package com.ncherkas.hazelcast.cloud.demo.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValidateRequest.class, name = "VALIDATE"),
        @JsonSubTypes.Type(value = KeepAliveRequest.class, name = "KEEP_ALIVE"),
})
public abstract class Request {

    private String type;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

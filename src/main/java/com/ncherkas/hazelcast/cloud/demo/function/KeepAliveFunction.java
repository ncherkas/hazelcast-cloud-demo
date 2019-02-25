package com.ncherkas.hazelcast.cloud.demo.function;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.ncherkas.hazelcast.cloud.demo.model.KeepAliveRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("keep_alive")
public class KeepAliveFunction implements Function<ScheduledEvent, Boolean> {

    private final KeepAliveService keepAliveService;

    public KeepAliveFunction(KeepAliveService keepAliveService) {
        this.keepAliveService = keepAliveService;
    }

    @Override
    public Boolean apply(ScheduledEvent scheduledEvent) {
        System.out.println("Processing scheduled event: " + scheduledEvent);

        keepAliveService.sendKeepAlive(new KeepAliveRequest());
        return true;
    }
}

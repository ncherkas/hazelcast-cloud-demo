package com.ncherkas.hazelcast.cloud.demo;

import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class AwsLambdaScheduledFunctionHandler extends SpringBootRequestHandler<ScheduledEvent, Void> {
}

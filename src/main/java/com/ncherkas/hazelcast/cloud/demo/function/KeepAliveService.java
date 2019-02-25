package com.ncherkas.hazelcast.cloud.demo.function;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
import com.ncherkas.hazelcast.cloud.demo.model.KeepAliveRequest;
import com.ncherkas.hazelcast.cloud.demo.model.Response;

public interface KeepAliveService {

    @LambdaFunction(functionName="ValidateFunction")
    Response sendKeepAlive(KeepAliveRequest keepAliveRequest);

}

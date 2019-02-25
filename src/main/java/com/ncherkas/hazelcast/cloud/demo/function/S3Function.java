package com.ncherkas.hazelcast.cloud.demo.function;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.IMap;
import com.ncherkas.hazelcast.cloud.demo.model.Airport;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component("download_from_s3")
public class S3Function implements Function<S3Event, Integer> {

    private final AmazonS3 amazonS3;
    private final IMap<String, Airport> airportsMap;
    private final ObjectMapper objectMapper;

    public S3Function(AmazonS3 amazonS3, IMap<String, Airport> airportsMap, ObjectMapper objectMapper) {
        this.amazonS3 = amazonS3;
        this.airportsMap = airportsMap;
        this.objectMapper = objectMapper;
    }

    @Override
    public Integer apply(S3Event s3Event) {
        System.out.println("Processing S3 event: " + s3Event);

        S3EventNotification.S3EventNotificationRecord record = s3Event.getRecords().get(0);

        String s3Key = record.getS3().getObject().getKey();
        String s3Bucket = record.getS3().getBucket().getName();

        System.out.println("S3 bucket " + s3Bucket + " and key " + s3Key);

        S3Object object = amazonS3.getObject(new GetObjectRequest(s3Bucket, s3Key));

        return copyToAirportsMap(object);
    }

    private int copyToAirportsMap(S3Object object) {
        try {
            try (S3ObjectInputStream objectContent = object.getObjectContent()) {
                Airport[] airports = objectMapper.readValue(objectContent, Airport[].class);
                Map<String, Airport> airportsByCode = Arrays.stream(airports)
                        .collect(toMap(Airport::getCode, Function.identity()));
                airportsMap.putAll(airportsByCode);
                return airports.length;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read S3 object contents");
        }
    }
}

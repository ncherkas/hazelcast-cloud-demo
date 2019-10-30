package com.ncherkas.hazelcast.cloud.demo.function;

import com.hazelcast.core.IMap;
import com.ncherkas.hazelcast.cloud.demo.model.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Function;

@Component("validate")
public class ValidateFunction implements Function<Request, Response> {

    public static final String AWS_NAME = "CloudDemoValidateFn";

    private static final int RADIUS_OF_THE_EARTH_M = 6_371_000;

    private final IMap<Integer, User> usersMap;
    private final IMap<String, Airport> airportsMap;

    public ValidateFunction(IMap<Integer, User> usersMap, IMap<String, Airport> airportsMap) {
        this.usersMap = usersMap;
        this.airportsMap = airportsMap;
    }

    @Override
    public Response apply(Request request) {
        switch (request.getType()) {
            case "VALIDATE":
                return applyValidateRequest((ValidateRequest) request);
            case "KEEP_ALIVE":
                applyKeepAliveRequest((KeepAliveRequest) request);
                return new Response("OK");
            default:
                throw new UnsupportedOperationException();
        }
    }

    private ValidateResponse applyValidateRequest(ValidateRequest validateRequest) {
        System.out.println("Handling Validate request");

        Integer userId = validateRequest.getUserId();
        User user = usersMap.get(userId);
        if (user == null) {
            usersMap.set(userId,
                    new User(userId, validateRequest.getAirportCode(), validateRequest.getTransactionTimestamp()));
            return new ValidateResponse(true, "User data saved for future validations");
        }

        Airport lastAirport = airportsMap.get(user.getLastCardUsePlace());
        Airport nextAirport = airportsMap.get(validateRequest.getAirportCode());

        // Time
        double minutes =
                Duration.between(user.getLastCardUseTimestamp(), validateRequest.getTransactionTimestamp()).toMinutes();

        // Distance
        double meters = haversine(nextAirport.getLatitude(), nextAirport.getLongitude(),
                lastAirport.getLatitude(), lastAirport.getLongitude());

        double speed = meters / minutes; // Speed = Distance / Time
        boolean valid = !(speed > 13000); // 800 km/hr == ~13000 m/min

        String message = valid ? "Transaction is OK" : "Transaction is suspicious";
        ValidateResponse validateResponse = new ValidateResponse(valid, message);

        user.setLastCardUsePlace(validateRequest.getAirportCode());
        user.setLastCardUseTimestamp(validateRequest.getTransactionTimestamp());
        usersMap.set(userId, user);

        return validateResponse;
    }

    private double haversine(double latitude, double longitude, double latitude2, double longitude2) {
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(latitude2);
        double long1 = Math.toRadians(longitude);
        double long2 = Math.toRadians(longitude2);

        double latDiff = lat1 - lat2;
        double longDiff = long1 - long2;

        double hav = Math.pow(Math.sin(latDiff / 2), 2)
                + Math.pow(Math.sin(longDiff / 2), 2)
                * Math.cos(lat1)
                * Math.cos(lat2);

        return 2 * RADIUS_OF_THE_EARTH_M * Math.asin(Math.sqrt(hav));
    }

    private void applyKeepAliveRequest(KeepAliveRequest keepAliveRequest) {
        System.out.println("Handling Keep Alive request");
    }
}

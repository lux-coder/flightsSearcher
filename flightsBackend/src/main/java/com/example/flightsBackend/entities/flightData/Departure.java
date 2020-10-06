package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class Departure {
    private String iataCode;
    private String at;
}

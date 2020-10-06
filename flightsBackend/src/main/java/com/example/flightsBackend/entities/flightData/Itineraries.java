package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class Itineraries {

    private String duration;
    private Segments[] segments;

}
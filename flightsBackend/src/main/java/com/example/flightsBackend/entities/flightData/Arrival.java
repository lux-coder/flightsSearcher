package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class Arrival {
    private String iataCode;
    private String terminal;
    private String at;
}

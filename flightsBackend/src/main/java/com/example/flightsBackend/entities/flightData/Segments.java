package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class Segments {

    private Departure departure;
    private Arrival arrival;
    private String carrierCode;
    private String number;
    private Aircraft aircraft;
    private Operating operating;
    private String duration;
    private String id;
    private Integer numberOfStops;
    private Boolean blacklistedInEU;

}

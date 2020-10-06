package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class FlightData {

    private String type;
    private Long id;
    private String source;
    private Boolean instantTicketingRequired;
    private Boolean nonHomogeneous;
    private Boolean oneWay;
    private String lastTicketingDate;
    private Integer numberOfBookableSeats;
    private Itineraries[] itineraries;
    private Price price;
}

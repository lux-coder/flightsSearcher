package com.example.flightsBackend.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Flight {
    private String originLocation;
    private String destinationLocation;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer adults;
    private Integer children;
    private String travelClass;
    private String currency;
    private Boolean nonStop;
}

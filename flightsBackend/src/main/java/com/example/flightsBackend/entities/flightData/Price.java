package com.example.flightsBackend.entities.flightData;

import lombok.Data;

@Data
public class Price {
    private String currency;
    private String total;
    private String base;
    private AdditionalServices[] additionalServices;
}

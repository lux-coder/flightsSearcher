package com.example.flightsBackend.services;

import com.example.flightsBackend.entities.Flight;
import com.example.flightsBackend.entities.flightData.FlightData;

import java.util.List;

public interface FlightsService {

    public List<FlightData> fetchFlights(Flight flight);
}

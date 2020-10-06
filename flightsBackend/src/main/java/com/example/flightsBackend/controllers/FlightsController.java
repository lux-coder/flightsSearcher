package com.example.flightsBackend.controllers;

import com.example.flightsBackend.entities.Flight;
import com.example.flightsBackend.entities.flightData.FlightData;
import com.example.flightsBackend.services.FlightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/flights")
public class FlightsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsController.class);

    @Autowired
    FlightsService flightsService;

    @RequestMapping(value = "fetchFlights",
            method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightData>> search(@RequestBody Flight flight) {
        LOGGER.info("In FlightsController with param: {}", flight);

        try {
            return new ResponseEntity<>(flightsService.fetchFlights(flight), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}

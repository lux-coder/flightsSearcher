package com.example.flightsBackend.controllers;

import com.example.flightsBackend.services.AirportService;
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
@RequestMapping("api/airport")
public class AirportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    @Autowired
    AirportService airportService;

    @RequestMapping(value = "search/{keyword}",
            method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> search(@PathVariable("keyword") String keyword) {
        LOGGER.info("In AirportController with param: {}", keyword);

        try {
            return new ResponseEntity<List<String>>(airportService.search(keyword), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }

    }
}

package com.example.flightsBackend.services;

import com.example.flightsBackend.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<String> search(String keyword) {
        return airportRepository.search(keyword);
    }
}

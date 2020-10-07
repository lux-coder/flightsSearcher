package com.example.flightsBackend.repositories;

import com.example.flightsBackend.entities.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("airportRepository")
public interface AirportRepository extends CrudRepository<Airport, Long> {

    @Query(value = "select iata, name, location from airports where iata like %:keyword%", nativeQuery = true)
    List<String> search(@Param("keyword") String keyword);
}

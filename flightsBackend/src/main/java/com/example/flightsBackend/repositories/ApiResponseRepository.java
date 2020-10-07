package com.example.flightsBackend.repositories;

import com.example.flightsBackend.entities.ApiResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("apiResponseRepository")
public interface ApiResponseRepository extends CrudRepository<ApiResponse, Long> {

    @Query(value = "select * from apiResponse ar where ar.request = :request", nativeQuery = true)
    ApiResponse findByRequest(@Param("request") String request);
}

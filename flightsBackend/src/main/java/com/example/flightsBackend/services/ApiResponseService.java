package com.example.flightsBackend.services;

import com.example.flightsBackend.entities.ApiResponse;

public interface ApiResponseService {

    ApiResponse saveApiResponse(ApiResponse apiResponse);

    ApiResponse getApiResponse(String request);

    void saveApiRequest(String request);

    void saveApiRequestAndResponse(String request, String response);
}

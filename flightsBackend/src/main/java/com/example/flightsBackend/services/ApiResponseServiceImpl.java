package com.example.flightsBackend.services;

import com.example.flightsBackend.entities.ApiResponse;
import com.example.flightsBackend.repositories.ApiResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("apiResponseService")
public class ApiResponseServiceImpl implements ApiResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiResponseServiceImpl.class);

    @Autowired
    ApiResponseRepository apiResponseRepository;

    public ApiResponse saveApiResponse(ApiResponse apiResponse) {
        return apiResponseRepository.save(apiResponse);
    }

    public ApiResponse getApiResponse(String request) {
        LOGGER.info("In getApiResponse");
        return apiResponseRepository.findByRequest(request);
    }

    @Override
    public void saveApiRequest(String request) {
        LOGGER.info("In saveApiRequest");
        apiResponseRepository.save(new ApiResponse(request));
    }

    public void saveApiRequestAndResponse(String request, String response){
        LOGGER.info("In saveApiRequestAndResponse");
        apiResponseRepository.save(new ApiResponse(request, response));
    }
}

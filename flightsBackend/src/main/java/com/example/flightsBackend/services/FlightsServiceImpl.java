package com.example.flightsBackend.services;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.flightsBackend.entities.ApiResponse;
import com.example.flightsBackend.entities.Flight;
import com.example.flightsBackend.entities.flightData.FlightData;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service("flightsService")
public class FlightsServiceImpl implements FlightsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsServiceImpl.class);

    @Autowired
    ApiResponseService apiResponseService;

    Amadeus amadeus = Amadeus
            .builder()
            .build();

    public List<FlightData> fetchFlights(Flight flight){
        LOGGER.info("flight request: {}", flight);

        String flightRequest = flight.toString();

        ApiResponse apiResponse = apiResponseService.getApiResponse(flightRequest);
        LOGGER.info("getApiResponse: {}", apiResponse);

        FlightOfferSearch[] flightOffers = null;
        List<JsonElement> flightsResponse = new ArrayList<>();
        JsonArray flightsResponseArray = new JsonArray();
        List<FlightData> flights = new ArrayList<>();
        Type listTypeFlightData = new TypeToken<List<FlightData>>() {}.getType();
        Type listTypeJsonElement = new TypeToken<List<JsonElement>>() {}.getType();
        Gson gson = new Gson();

        if (apiResponse == null) {

            String originLocationCode = flight.getOriginLocation().substring(0,3);
            LOGGER.info("originLocationCode: {}",originLocationCode);
            String destinationLocationCode = flight.getDestinationLocation().substring(0,3);
            LOGGER.info("destinationLocationCode: {}",destinationLocationCode);
            Integer adults = flight.getAdults();
            LOGGER.info("adults: {}", adults);
            Integer children = flight.getChildren() == null ? 0 : flight.getChildren();
            LOGGER.info("children: {}", children);
            String travelClass = flight.getTravelClass();
            LOGGER.info("travelClass: {}", travelClass);
            String currency = flight.getCurrency() == null ? "EUR" : flight.getCurrency();
            LOGGER.info("currency: {}", currency);
            Boolean nonStop = flight.getNonStop() == null ? false : flight.getNonStop();
            LOGGER.info("nonStop: {}", nonStop);

            if (flight.getTravelClass() == null && flight.getReturnDate() == null) {
                flightOffers = withoutClassAndDate(flight);
            } else if (flight.getReturnDate() == null) {
                flightOffers = withoutDate(flight);
            } else if (flight.getTravelClass() == null) {
                flightOffers = withoutClass(flight);
            } else {
                flightOffers = withAllData(flight);
            }

            LOGGER.info("FlightOfferSearch");
            for (FlightOfferSearch fosResponse : flightOffers) {
                flightsResponse.add(fosResponse.getResponse().getData());
                flightsResponseArray.add(fosResponse.getResponse().getData());
            }

            LOGGER.info("JsonElement");
            for (JsonElement element : flightsResponse) {
                flights = gson.fromJson(element, listTypeFlightData);
            }

            try {
                apiResponseService.saveApiRequestAndResponse(flight.toString(), flightsResponseArray.toString());
            } catch (Exception e){
                LOGGER.info(e.getMessage());
            }
            return flights;
        } else {
            LOGGER.info("Fetching from database");
            String res = apiResponse.getResponse();
            try {
                flightsResponse = gson.fromJson(res, listTypeJsonElement);
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }

            LOGGER.info("JsonElement");
            for (JsonElement element : flightsResponse) {
                flights = gson.fromJson(element, listTypeFlightData);
            }
            return flights;
        }
    }

    private FlightOfferSearch[] withAllData(Flight flight) {
        FlightOfferSearch[] fo = null;
        try {
            fo = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flight.getOriginLocation().substring(0,3))
                            .and("destinationLocationCode", flight.getDestinationLocation().substring(0,3))
                            .and("departureDate", flight.getDepartureDate())
                            .and("returnDate", flight.getReturnDate())
                            .and("adults", flight.getAdults())
                            .and("children", flight.getChildren() == null ? 0 : flight.getChildren())
                            .and("travelClass", flight.getTravelClass())
                            .and("currencyCode", flight.getCurrency() == null ? "EUR" : flight.getCurrency())
                            .and("nonStop", flight.getNonStop() == null ? false : flight.getNonStop())
                            .and("max", 50));
        } catch (ResponseException e) {
            LOGGER.info(e.getCode());
            LOGGER.info(e.getMessage());
        }
        return fo;
    }

    private FlightOfferSearch[] withoutClass(Flight flight) {
        FlightOfferSearch[] fo = null;
        try {
            fo = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flight.getOriginLocation().substring(0,3))
                            .and("destinationLocationCode", flight.getDestinationLocation().substring(0,3))
                            .and("departureDate", flight.getDepartureDate())
                            .and("returnDate", flight.getReturnDate())
                            .and("adults", flight.getAdults())
                            .and("children", flight.getChildren() == null ? 0 : flight.getChildren())
                            .and("currencyCode", flight.getCurrency() == null ? "EUR" : flight.getCurrency())
                            .and("nonStop", flight.getNonStop() == null ? false : flight.getNonStop())
                            .and("max", 50));
        } catch (ResponseException e) {
            LOGGER.info(e.getCode());
            LOGGER.info(e.getMessage());
        }
        return fo;
    }

    private FlightOfferSearch[] withoutDate(Flight flight) {
        FlightOfferSearch[] fo = null;
        try {
            fo = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flight.getOriginLocation().substring(0,3))
                            .and("destinationLocationCode", flight.getDestinationLocation().substring(0,3))
                            .and("departureDate", flight.getDepartureDate())
                            .and("adults", flight.getAdults())
                            .and("children", flight.getChildren() == null ? 0 : flight.getChildren())
                            .and("travelClass", flight.getTravelClass())
                            .and("currencyCode", flight.getCurrency() == null ? "EUR" : flight.getCurrency())
                            .and("nonStop", flight.getNonStop() == null ? false : flight.getNonStop())
                            .and("max", 50));
        } catch (ResponseException e) {
            LOGGER.info(e.getCode());
            LOGGER.info(e.getMessage());
        }
        return fo;
    }

    private FlightOfferSearch[] withoutClassAndDate(Flight flight) {
        FlightOfferSearch[] fo = null;
        try {
            fo = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", flight.getOriginLocation().substring(0,3))
                            .and("destinationLocationCode", flight.getDestinationLocation().substring(0,3))
                            .and("departureDate", flight.getDepartureDate())
                            .and("adults", flight.getAdults())
                            .and("children", flight.getChildren() == null ? 0 : flight.getChildren())
                            .and("currencyCode", flight.getCurrency() == null ? "EUR" : flight.getCurrency())
                            .and("nonStop", flight.getNonStop() == null ? false : flight.getNonStop())
                            .and("max", 50));
        } catch (ResponseException e) {
            LOGGER.info(e.getCode());
            LOGGER.info(e.getMessage());
        }
        return fo;
    }
}

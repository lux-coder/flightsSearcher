package com.example.flightsBackend.services;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.flightsBackend.entities.Flight;
import com.example.flightsBackend.entities.flightData.FlightData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service("flightsService")
public class FlightsServiceImpl implements FlightsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsServiceImpl.class);

    Amadeus amadeus = Amadeus
            .builder()
            .build();

    public List<FlightData> fetchFlights(Flight flight){

        FlightOfferSearch[] flightOffers = null;

        String originLocationCode = flight.getOriginLocation().substring(0,3);
        LOGGER.info("originLocationCode: {}",originLocationCode);
        String destinationLocationCode = flight.getDestinationLocation().substring(0,3);
        LOGGER.info("destinationLocationCode: {}",destinationLocationCode);

        try {
            flightOffers = amadeus.shopping.flightOffersSearch.get(
                    Params.with("originLocationCode", originLocationCode)
                            .and("destinationLocationCode", destinationLocationCode)
                            .and("departureDate", flight.getDepartureDate())
                            .and("returnDate", flight.getReturnDate())
                            .and("currencyCode", "HRK")
                            .and("adults", flight.getAdults())
                            .and("max", 50));
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        List<JsonElement> flightsResponse = new ArrayList<>();
        List<FlightData> flights = new ArrayList<>();
		Type listType = new TypeToken<List<FlightData>>() {}.getType();
        Gson gson = new Gson();
//
        LOGGER.info("FlightOfferSearch");
        for (FlightOfferSearch fosResponse : flightOffers) {
            flightsResponse.add(fosResponse.getResponse().getData());
        }

        LOGGER.info("JsonElement");
        for (JsonElement element : flightsResponse) {
            flights = gson.fromJson(element, listType);
//            System.out.println(element);
        }



//        LOGGER.info("FlightData");
//        for (FlightData flightData: flights) {
//            System.out.println(flightData.toString());
//            System.out.println("----------------------------------------------");
//        }


        return flights;


    }


}

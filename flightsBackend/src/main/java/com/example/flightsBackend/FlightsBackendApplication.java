package com.example.flightsBackend;

import com.amadeus.exceptions.ResponseException;
import com.example.flightsBackend.entities.flightData.FlightData;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

@SpringBootApplication
public class FlightsBackendApplication {

	public static void main(String[] args) throws ResponseException {

		SpringApplication.run(FlightsBackendApplication.class, args);


//
//		Location[] locations = amadeus.referenceData.locations.get(Params
//				.with("keyword", "LON")
//				.and("subType", Locations.ANY));
//
//		//JsonElement lokacije = locations[0].getResponse().getData();
//
//		//Type listType = new TypeToken<List<String>>() {}.getType();
//
//		//List<String> listaLokacija = new Gson().fromJson(lokacije, listType);
//
//		List<String> lokacije = new ArrayList<>();
//
//		for (Location loc : locations) {
//			lokacije.add(loc.toString());
//		}
//
//		System.out.println(lokacije.get(0));
//		System.out.println(lokacije.get(1));
//		System.out.println(lokacije.get(2));
//		System.out.println(lokacije.get(3));
//
//		System.out.println("---------------------------------------------------------------------------------");
//

//
//

//		Gson gson = new Gson();
//		FlightData[] data = new FlightData[0];
//
//		try (Reader reader = new FileReader("C:\\Users\\lux-coder\\Desktop\\flightData2.json")) {
//
//
//			data = gson.fromJson(reader, FlightData[].class);
//
//			System.out.println(Arrays.toString(data));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		for (FlightData dat: data) {
//			System.out.println(dat);
//			System.out.println("--------------------------------------");
//
//		}


	}

}

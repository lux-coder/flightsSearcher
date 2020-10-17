package com.example.flightsBackend.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {

    @Bean
    public Counter flightRequestsCounter(MeterRegistry meterRegistry) {
        return Counter
                .builder("api.flights.fetchFlights.searched")
                .description("Number of searched flights")
                .register(meterRegistry);
    }

    @Bean
    public Timer flightRequestTimer(MeterRegistry meterRegistry) {
        return Timer
                .builder("api.flights.fetchedFlights.time")
                .description("Time taken to fetch available flights")
                .register(meterRegistry);
    }

    @Bean
    public InMemoryHttpTraceRepository inMemoryHttpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}

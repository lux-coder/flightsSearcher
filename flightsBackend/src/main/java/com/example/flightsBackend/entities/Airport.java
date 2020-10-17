package com.example.flightsBackend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String iata;
    private String name;
    private String location;
    private String country;

    public Airport(String iata, String name, String location, String country){
        this.iata = iata;
        this.name = name;
        this.location = location;
        this.country = country;
    }
}

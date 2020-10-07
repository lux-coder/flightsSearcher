package com.example.flightsBackend.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Data
@Entity
@Table(name = "apiresponse")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ApiResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String request;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String response;

    public ApiResponse() {
    }

    public ApiResponse(String request) {
        this.request = request;
    }

    public ApiResponse(String request, String response) {
        this.request = request;
        this.response = response;
    }

    public ApiResponse(Long id, String request, String response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }
}

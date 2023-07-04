package com.example.secondspring1.dto.director;

import com.example.secondspring1.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class DirectorResponse {

    @Id
    @JsonProperty("id")
    private UUID id;

    private String name;
    private String biography;
    private String birth_date;
    private String nationality;


    private Set<Movie> movies;
}
package com.example.secondspring1.dto.movie;


import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Director;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class MovieResponse {

    @Id
    @JsonProperty("id")
    private UUID id;
    private String title;
    private String description;
    private String release_date;
    private String duration;
    private String genre;

    private Set<Actor> actors;
    private Set<Director> directors;
}

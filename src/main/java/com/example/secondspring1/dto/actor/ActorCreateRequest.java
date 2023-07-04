package com.example.secondspring1.dto.actor;

import com.example.secondspring1.model.Director;
import com.example.secondspring1.model.Movie;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class ActorCreateRequest {

    private String name;
    private String biography;
    private String birth_date;
    private String nationality;


    private Set<Movie> movies;

}

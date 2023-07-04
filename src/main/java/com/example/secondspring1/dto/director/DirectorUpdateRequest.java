package com.example.secondspring1.dto.director;

import com.example.secondspring1.model.Movie;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DirectorUpdateRequest {

    private String name;
    private String biography;
    private String birth_date;
    private String nationality;


    private Set<Movie> movies;
}
package com.example.secondspring1.dto.movie;

import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Director;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MovieUpdateRequest {

    private String title;
    private String description;
    private String release_date;
    private String duration;
    private String genre;

    private Set<Actor> actors;
    private Set<Director> directors;
}

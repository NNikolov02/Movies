package com.example.secondspring1.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MovieDto {
    private UUID id;


    private String title;
    private String description;
    private String release_date;
    private String duration;
    private String genre;


}

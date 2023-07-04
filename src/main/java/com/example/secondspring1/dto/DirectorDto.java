package com.example.secondspring1.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DirectorDto {

    private UUID id;

    private String name;
    private String biography;
    private String birth_date;
    private String nationality;

}

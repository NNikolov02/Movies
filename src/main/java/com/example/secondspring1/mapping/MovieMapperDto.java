package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.MovieDto;
import com.example.secondspring1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapperDto {


    MovieDto modelRoDto(Movie movie);

    Movie dtoModel(MovieDto movieDto);



}
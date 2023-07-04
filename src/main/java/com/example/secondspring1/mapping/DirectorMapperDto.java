package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.DirectorDto;
import com.example.secondspring1.dto.MovieDto;
import com.example.secondspring1.model.Director;
import com.example.secondspring1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DirectorMapperDto {


    DirectorDto modelRoDto(Director director);

    Director dtoModel(DirectorDto directorDto);



}
package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.ActorDto;
import com.example.secondspring1.dto.MovieDto;
import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActorMapperDto {


    ActorDto modelRoDto(Actor actor);

   Actor dtoModel(ActorDto actorDto);



}
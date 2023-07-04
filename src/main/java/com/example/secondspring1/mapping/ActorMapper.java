package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.actor.ActorCreateRequest;
import com.example.secondspring1.dto.actor.ActorResponse;
import com.example.secondspring1.dto.actor.ActorUpdateRequest;
import com.example.secondspring1.dto.movie.MovieCreateRequest;
import com.example.secondspring1.dto.movie.MovieResponse;
import com.example.secondspring1.dto.movie.MovieUpdateRequest;
import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {MovieMapperDto.class})
public interface ActorMapper {




    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies",  ignore = true)
    Actor modelFromCreateRequest(ActorCreateRequest actorCreateDto);

    ActorResponse responseFromModel(Actor actor);
    @Mapping(target = "movies",ignore = true)
    @Mapping(target = "name",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "biography", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "birth_date", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "nationality", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(ActorUpdateRequest actorUpdateDto, @MappingTarget Actor actor);
    List<ActorResponse> listOfModelToListOfDto(List<Actor>actors);

    List<ActorResponse> listOfModelToListOfDto(Iterable<Actor> all);


}

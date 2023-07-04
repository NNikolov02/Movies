package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.movie.MovieCreateRequest;
import com.example.secondspring1.dto.movie.MovieResponse;
import com.example.secondspring1.dto.movie.MovieUpdateRequest;
import com.example.secondspring1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {ActorMapperDto.class, DirectorMapperDto.class})
public interface MovieMapper {




    @Mapping(target = "id", ignore = true)
    @Mapping(target = "actors",  ignore = true)
    @Mapping(target = "directors", ignore = true)
    Movie modelFromCreateRequest(MovieCreateRequest movieCreateDto);

    MovieResponse responseFromModel(Movie movie);
    @Mapping(target = "actors",ignore = true)
    @Mapping(target = "directors", ignore = true)
    @Mapping(target = "title",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "genre", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "release_date", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "duration", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(MovieUpdateRequest  movieUpdateDto, @MappingTarget Movie movie);
    List<MovieResponse> listOfModelToListOfDto(List<Movie>movies);

    List<MovieResponse> listOfModelToListOfDto(Iterable<Movie> all);


}

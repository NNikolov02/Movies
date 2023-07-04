package com.example.secondspring1.mapping;

import com.example.secondspring1.dto.actor.ActorCreateRequest;
import com.example.secondspring1.dto.actor.ActorResponse;
import com.example.secondspring1.dto.actor.ActorUpdateRequest;
import com.example.secondspring1.dto.director.DirectorCreateRequest;
import com.example.secondspring1.dto.director.DirectorResponse;
import com.example.secondspring1.dto.director.DirectorUpdateRequest;
import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {MovieMapperDto.class})
public interface DirectorMapper {




    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies",  ignore = true)
    Director modelFromCreateRequest(DirectorCreateRequest directorCreateDto);

    DirectorResponse responseFromModel(Director director);
    @Mapping(target = "movies",ignore = true)
    @Mapping(target = "name",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "biography", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "birth_date", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "nationality", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(DirectorUpdateRequest directorUpdateDto, @MappingTarget Director director);
    List<DirectorResponse> listOfModelToListOfDto(List<Director>directors);

    List<DirectorResponse> listOfModelToListOfDto(Iterable<Director> all);


}
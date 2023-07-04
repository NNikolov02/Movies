package com.example.secondspring1.Web;


import com.example.secondspring1.dto.actor.ActorApiPage;
import com.example.secondspring1.dto.actor.ActorCreateRequest;
import com.example.secondspring1.dto.actor.ActorResponse;
import com.example.secondspring1.dto.actor.ActorUpdateRequest;

import com.example.secondspring1.error.InvalidObjectException;
import com.example.secondspring1.mapping.ActorMapper;

import com.example.secondspring1.model.Actor;

import com.example.secondspring1.model.Movie;
import com.example.secondspring1.service.ActorService;

import com.example.secondspring1.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
public class ActorController {

    @Autowired
    private ObjectValidator validator;
    @Autowired
    private ActorService actorService;
    @Autowired
    private ActorMapper actorMapper;
    private final Integer Page_Size = 10;


    @GetMapping(name = "",produces = "application/json")
    public ActorApiPage<ActorResponse> getAllActors(

            @RequestParam(required = false,defaultValue = "0") Integer currPage){
        Page<ActorResponse> actorPage =
                actorService.fetchAll(currPage, Page_Size).map(actorMapper::responseFromModel);
        return new ActorApiPage<>(actorPage);


    }

    @GetMapping("/{actorId}")
    public ResponseEntity<Actor> getActor(@PathVariable String actorId) {
        // Retrieve actor data from the database using actorId
        Actor actor = actorService.findById(actorId);

        if (actor != null) {
            convertMovieIdsToLinks(actor); // Convert movie IDs to links
            return ResponseEntity.ok(actor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void convertMovieIdsToLinks(Actor actor) {
        Set<Movie> movies = actor.getMovies();
        for (Movie movie : movies) {
            String movieId = String.valueOf(movie.getId());
            String movieLink = "http://localhost:8081/movies/" + movieId;
            movie.setLink(movieLink);
        }
    }


    @DeleteMapping("/{actorId}")
    public void deleteMovieById(@PathVariable String actorId){actorService.deleteById(actorId);}


    @PostMapping("")
    public ResponseEntity<ActorResponse> createActor(@RequestBody ActorCreateRequest actorDto){

        Map<String, String> validationErrors = validator.validate(actorDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Actor Create", validationErrors);
        }

        Actor mappedActor = actorMapper.modelFromCreateRequest(actorDto);

        Actor savedActor = actorService.save(mappedActor);

        ActorResponse actorResponse = actorMapper.responseFromModel(savedActor);


        return ResponseEntity.status(201).body(actorResponse);

    }

    @PatchMapping("{actorId}")
    public ResponseEntity<ActorResponse> updateActor(@PathVariable String actorId, @RequestBody ActorUpdateRequest actorDto) {

        Map<String, String> validationErrors = validator.validate(actorDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Actor Create", validationErrors);
        }

        Actor findActor = actorService.findById(actorId);

        actorMapper.updateModelFromDto(actorDto, findActor);

        Actor savedActor = actorService.save(findActor);

        ActorResponse actorResponse = actorMapper.responseFromModel(savedActor);

        return ResponseEntity.status(200).body(actorResponse);

    }



}




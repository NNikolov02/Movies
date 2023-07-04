package com.example.secondspring1.Web;

import com.example.secondspring1.dto.movie.MovieApiPage;
import com.example.secondspring1.dto.movie.MovieCreateRequest;
import com.example.secondspring1.dto.movie.MovieResponse;
import com.example.secondspring1.dto.movie.MovieUpdateRequest;
import com.example.secondspring1.error.InvalidObjectException;
import com.example.secondspring1.mapping.MovieMapper;
import com.example.secondspring1.model.Movie;
import com.example.secondspring1.service.MovieService;
import com.example.secondspring1.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    @Autowired
    private ObjectValidator validator;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;
    private final Integer Page_Size = 10;


    @GetMapping(name = "",produces = "application/json")

    public MovieApiPage<MovieResponse> getAllMovies(

            @RequestParam(required = false,defaultValue = "0") Integer currPage){
        Page<MovieResponse> moviePage =
               movieService.fetchAll(currPage, Page_Size).map(movieMapper::responseFromModel);
        return new MovieApiPage<>(moviePage);


    }


    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable String movieId) {
        Movie movie = movieService.findById(movieId);

        return ResponseEntity.ok(movieMapper.responseFromModel(movie));
    }
    @DeleteMapping("/{movieId}")
    public void deleteMovieById(@PathVariable String movieId) {
        movieService.deleteById(movieId);
    }
    @PostMapping("")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieCreateRequest movieDto) {

        Map<String, String> validationErrors = validator.validate(movieDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Movie Create", validationErrors);
        }


        Movie mappedMovie = movieMapper.modelFromCreateRequest(movieDto);

        Movie savedMovie = movieService.save(mappedMovie);

        MovieResponse responseMovie = movieMapper.responseFromModel(savedMovie);

        return ResponseEntity.status(201).body(responseMovie);

    }

    @PatchMapping("/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable String movieId, @RequestBody MovieUpdateRequest movieDto){
        Map<String, String> validationErrors = validator.validate(movieDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Movie Create", validationErrors);
        }
        Movie currentMovie = movieService.findById(movieId);

        movieMapper.updateModelFromDto(movieDto, currentMovie);

        Movie updatedMovie = movieService.save(currentMovie);

        MovieResponse responseMovie = movieMapper.responseFromModel(updatedMovie);

        return ResponseEntity.status(200).body(responseMovie);


    }



}

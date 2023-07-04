package com.example.secondspring1.service;

import com.example.secondspring1.dto.movie.MovieResponse;
import com.example.secondspring1.error.NotFoundObjectException;
import com.example.secondspring1.model.Movie;
import com.example.secondspring1.repository.MoviePagingRepository;
import com.example.secondspring1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
public class MovieService {
    @Autowired
    private MovieRepository repo;

    @Autowired
    private MoviePagingRepository pagingRepo;

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

    public Page<Movie> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }
    public Movie findById(String movieId) {
        return repo.findById(UUID.fromString(movieId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Movie Not Found", Movie.class.getName(), movieId);
        });
    }

    public void deleteById(String movieId) {
        repo.deleteById(UUID.fromString(movieId));
    }
}
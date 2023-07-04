package com.example.secondspring1.repository;

import com.example.secondspring1.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MovieRepository extends CrudRepository<Movie, UUID> {

}

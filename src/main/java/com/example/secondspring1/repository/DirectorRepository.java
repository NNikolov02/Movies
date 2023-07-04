package com.example.secondspring1.repository;

import com.example.secondspring1.model.Director;
import com.example.secondspring1.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DirectorRepository extends CrudRepository<Director, UUID> {

}

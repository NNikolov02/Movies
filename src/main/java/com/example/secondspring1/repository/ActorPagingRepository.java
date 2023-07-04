package com.example.secondspring1.repository;

import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Director;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActorPagingRepository extends PagingAndSortingRepository<Actor, UUID> {

}
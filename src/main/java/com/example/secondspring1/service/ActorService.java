package com.example.secondspring1.service;

import com.example.secondspring1.error.NotFoundObjectException;
import com.example.secondspring1.model.Actor;
import com.example.secondspring1.model.Movie;
import com.example.secondspring1.repository.ActorPagingRepository;
import com.example.secondspring1.repository.ActorRepository;
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
public class ActorService {
    @Autowired
    private ActorRepository repo;

    @Autowired
    private ActorPagingRepository pagingRepo;

    public Actor save(Actor actor) {
        return repo.save(actor);
    }

    public Page<Actor> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }
    public Actor findById(String actorId) {
        return repo.findById(UUID.fromString(actorId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Actor Not Found", Movie.class.getName(), actorId);
        });
    }

    public void deleteById(String actorId) {
        repo.deleteById(UUID.fromString(actorId));
    }
}
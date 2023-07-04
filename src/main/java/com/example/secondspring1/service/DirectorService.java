package com.example.secondspring1.service;

import com.example.secondspring1.error.NotFoundObjectException;
import com.example.secondspring1.model.Director;
import com.example.secondspring1.model.Movie;
import com.example.secondspring1.repository.DirectorPagingRepository;
import com.example.secondspring1.repository.DirectorRepository;
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
public class DirectorService {
    @Autowired
    private DirectorRepository repo;

    @Autowired
    private DirectorPagingRepository pagingRepo;

    public Director save(Director  director) {
        return repo.save(director);
    }

    public Page<Director> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }
    public Director findById(String directorId) {
        return repo.findById(UUID.fromString(directorId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Director Not Found", Movie.class.getName(), directorId);
        });
    }

    public void deleteById(String directorId) {
        repo.deleteById(UUID.fromString(directorId));
    }
}
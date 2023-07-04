package com.example.secondspring1.Web;

import com.example.secondspring1.dto.actor.ActorApiPage;
import com.example.secondspring1.dto.actor.ActorResponse;
import com.example.secondspring1.dto.director.DirectorApiPage;
import com.example.secondspring1.dto.director.DirectorCreateRequest;
import com.example.secondspring1.dto.director.DirectorResponse;
import com.example.secondspring1.dto.director.DirectorUpdateRequest;
import com.example.secondspring1.error.InvalidObjectException;
import com.example.secondspring1.mapping.DirectorMapper;
import com.example.secondspring1.model.Director;
import com.example.secondspring1.service.DirectorService;
import com.example.secondspring1.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/directors")
@AllArgsConstructor
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private ObjectValidator validator;


    private final Integer Page_Size = 10;


    @GetMapping(name = "",produces = "application/json")
    public DirectorApiPage<DirectorResponse> getAllActors(

            @RequestParam(required = false,defaultValue = "0") Integer currPage){
        Page<DirectorResponse> directorPage =
                directorService.fetchAll(currPage, Page_Size).map(directorMapper::responseFromModel);
        return new DirectorApiPage<>(directorPage);


    }

    @GetMapping("/directorsId")
    public ResponseEntity<DirectorResponse> getDirectors(@PathVariable String directorId){
        Director director = directorService.findById(directorId);

        return ResponseEntity.ok(directorMapper.responseFromModel(director));

    }

    @DeleteMapping("/directorsId")
    public void deleteDirectors(@PathVariable String directorId){directorService.deleteById(directorId);}

    @PostMapping("")
    public ResponseEntity<DirectorResponse> createDirector(@RequestBody DirectorCreateRequest directorDto){

        Map<String, String> validationErrors = validator.validate(directorDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Director Create", validationErrors);
        }

        Director mappedDirector = directorMapper.modelFromCreateRequest(directorDto);

        Director savedDirector = directorService.save(mappedDirector);

        DirectorResponse directorResponse = directorMapper.responseFromModel(savedDirector);


        return ResponseEntity.status(201).body(directorResponse);
    }

    @PatchMapping("/{directorId}")
    public ResponseEntity<DirectorResponse> updateDirector(@PathVariable String directorId, @RequestBody DirectorUpdateRequest directorDto){
        Map<String, String> validationErrors = validator.validate(directorDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Director Create", validationErrors);

        }
        Director findDirector = directorService.findById(directorId);
        directorMapper.updateModelFromDto(directorDto,findDirector);

        Director savedDirector = directorService.save(findDirector);
        DirectorResponse directorResponse = directorMapper.responseFromModel(savedDirector);


        return ResponseEntity.status(200).body(directorResponse);
    }
}

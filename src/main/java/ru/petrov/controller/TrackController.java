package ru.petrov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.petrov.model.Track;
import ru.petrov.model.exception.TrackNotFound;
import ru.petrov.repository.TrackRepository;
import ru.petrov.service.TrackService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public ResponseEntity<Page<Track>> getList(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(trackService.findAll(pageable),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getOne(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(trackService.findById(id),HttpStatus.OK);
        } catch (TrackNotFound e){
            log.error(e.toString());
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

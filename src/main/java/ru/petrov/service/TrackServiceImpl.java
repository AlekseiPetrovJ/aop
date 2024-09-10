package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.model.Track;
import ru.petrov.model.exception.TrackNotFound;
import ru.petrov.repository.TrackRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;

    public Page<Track> findAll(Pageable pageable) {
        return trackRepository.findAll(pageable);
    }

//    @Async
    @Transactional
    public Track save(Track track){
        return trackRepository.save(track);
    }

    public Track findById(Long id){
        return trackRepository.findById(id).orElseThrow(() ->
                new TrackNotFound(String.format("track with ID:%s not found", id)));
    }

}

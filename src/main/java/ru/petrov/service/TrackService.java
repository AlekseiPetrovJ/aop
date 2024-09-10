package ru.petrov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.petrov.model.Track;

public interface TrackService {
    Page<Track> findAll(Pageable pageable);

    Track save(Track track);

    Track findById(Long id);
}

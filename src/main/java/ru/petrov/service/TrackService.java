package ru.petrov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.petrov.dto.StatisticTrackDto;
import ru.petrov.dto.TrackDto;
import ru.petrov.model.Track;

import java.util.List;
import java.util.Map;

public interface TrackService {
    Page<TrackDto> findAll(Pageable pageable);
    Track save(Track track);
    TrackDto findById(Long id);
    Map<String, List<TrackDto>> getTracksGroupedByMethodName();

    List<StatisticTrackDto> getStatisticTrack();
}

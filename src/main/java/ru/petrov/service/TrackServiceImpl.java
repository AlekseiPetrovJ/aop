package ru.petrov.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.dto.StatisticTrackDto;
import ru.petrov.dto.TrackDto;
import ru.petrov.model.Track;
import ru.petrov.model.exception.TrackNotFound;
import ru.petrov.repository.TrackRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final ModelMapper modelMapper;

    public Page<TrackDto> findAll(Pageable pageable) {
        return trackRepository.findAll(pageable)
                .map(track -> modelMapper.map(track, TrackDto.class));
    }

    @Transactional
    public Track save(Track track){
        return trackRepository.save(track);
    }

    public TrackDto findById(Long id){
        Track track = trackRepository.findById(id).orElseThrow(() ->
                new TrackNotFound(String.format("track with ID:%s not found", id)));
        return modelMapper.map(track, TrackDto.class);
    }

    public Map<String, List<TrackDto>> getTracksGroupedByMethodName() {
        return trackRepository.findAll()
                .stream().map(track -> modelMapper.map(track, TrackDto.class))
                .collect(Collectors.groupingBy(TrackDto::getMethodName));
    }

    public List<StatisticTrackDto> getStatisticTrack() {
        return trackRepository.getStatisticTrack().stream().toList();
    }


}

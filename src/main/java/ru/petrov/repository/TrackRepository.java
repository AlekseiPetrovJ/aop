package ru.petrov.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.petrov.dto.StatisticTrackDto;
import ru.petrov.model.Track;

import java.util.Collection;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    @Query("SELECT new ru.petrov.dto.StatisticTrackDto(t.methodName," +
            " AVG(t.executionTime), MAX(t.executionTime), MIN(t.executionTime), " +
            "COUNT(t.id)) FROM Track t GROUP BY t.methodName ORDER BY t.methodName")
    Collection<StatisticTrackDto> getStatisticTrack();
}

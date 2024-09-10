package ru.petrov.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petrov.model.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
}

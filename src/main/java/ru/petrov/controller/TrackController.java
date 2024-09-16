package ru.petrov.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.petrov.dto.StatisticTrackDto;
import ru.petrov.dto.TrackDto;
import ru.petrov.model.exception.TrackNotFound;
import ru.petrov.service.TrackService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @Operation(summary = "Получить все замеры времени")
    @GetMapping
    public ResponseEntity<Page<TrackDto>> getTracks(@ParameterObject Pageable pageable) {
        return new ResponseEntity<>(trackService.findAll(pageable),HttpStatus.OK);
    }

    @Operation(summary = "Получить замер времени по id", description = """
            В переменной пути передается id замера.
            Возвращается замер с таким id. При отсутствии замера с таким id возвращается код ошибки 404.
            """)
    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getOne(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(trackService.findById(id),HttpStatus.OK);
        } catch (TrackNotFound e){
            log.error(e.toString());
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Получить все замеры времени с группировкой", description = """
            Возвращаются все замеры времени сгруппированные по имени метода.
            """)
    @GetMapping("/grouped")
    public ResponseEntity<Map<String, List<TrackDto>>> getTracksGroupedByMethodName() {
        return new ResponseEntity<>(trackService.getTracksGroupedByMethodName(), HttpStatus.OK);
    }

    @Operation(summary = "Получить статистику по замерам", description = """
            Возвращаются min, max, avr, count для группировки по имени метода.
            """)

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticTrackDto>> getStatistic() {
        return new ResponseEntity<>(trackService.getStatisticTrack(), HttpStatus.OK);
    }
}

package ru.petrov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.petrov.model.Track;

import java.time.LocalDateTime;

/**
 * DTO for {@link Track}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private Long id;
    @Schema(description = "Дата и время")
    private LocalDateTime localDateTime;
    @Schema(description = "Название метода. " +
            "В случаях возникновения исключения, выводится текст исключения " +
            "и последний метод из StackTrace ",
            example = "execution(void ru.petrov.util.DemoClass.demoAsyncMethod(int))")
    private String methodName;
    @Schema(description = "Время исполнения метода в ms.")
    private long executionTime;
}
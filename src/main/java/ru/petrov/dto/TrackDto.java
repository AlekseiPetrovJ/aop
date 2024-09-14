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
    Long id;
    @Schema(description = "Дата и время")
    LocalDateTime localDateTime;
    @Schema(description = "Название метода. " +
            "В случаях возникновения исключения, выводится текст исключения " +
            "и последний метод из StackTrace ",
            example = "execution(void ru.petrov.util.DemoClass.demoAsyncMethod(int))")
    String methodName;
    @Schema(description = "Время исполнения метода в ms.")
    long executionTime;
}
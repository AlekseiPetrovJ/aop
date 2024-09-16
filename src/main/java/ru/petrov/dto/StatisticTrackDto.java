package ru.petrov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticTrackDto {
    @Schema(description = "Название метода. " +
            "В случаях возникновения исключения, выводится текст исключения " +
            "и последний метод из StackTrace ",
            example = "execution(void ru.petrov.util.DemoClass.demoAsyncMethod(int))")
    private String methodName;
    @Schema(description = "Среднее время исполнения метода в ms.")
    private double avrExecutionTime;
    @Schema(description = "Максимальное время исполнения метода в ms.")
    private long maxExecutionTime;
    @Schema(description = "Минимальное время исполнения метода в ms.")
    private long minExecutionTime;
    @Schema(description = "Количество вызовов метода")
    private long countExecution;

}

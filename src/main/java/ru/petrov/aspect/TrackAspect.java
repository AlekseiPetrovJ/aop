package ru.petrov.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.petrov.model.Track;
import ru.petrov.repository.TrackRepository;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackAspect {
    private final TrackRepository trackRepository;

    @Around("@annotation(ru.petrov.annotations.TrackTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        trackRepository.save(new Track(null, LocalDateTime.now(), joinPoint.toString(),
                System.currentTimeMillis() - start));
        return result;
    }

    @Async
    @Around("@annotation(ru.petrov.annotations.TrackAsyncTime)")
    public Object measureExecutionTimeAsync(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        trackRepository.save(new Track(null, LocalDateTime.now(), joinPoint.toString(),
                System.currentTimeMillis() - start));
        return result;
    }
}
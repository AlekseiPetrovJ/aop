package ru.petrov.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.petrov.annotations.TrackAsyncTime;
import ru.petrov.annotations.TrackTime;
import ru.petrov.model.Track;
import ru.petrov.service.TrackService;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackAspect {
    private final TrackService trackService;

    @Around("@annotation(ru.petrov.annotations.TrackTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        checkForConflicts(joinPoint);
        return saveTrack(joinPoint);
    }

    @Async
    @Around("@annotation(ru.petrov.annotations.TrackAsyncTime)")
    public Object measureExecutionTimeAsync(ProceedingJoinPoint joinPoint) throws Throwable {
        checkForConflicts(joinPoint);
        return saveTrack(joinPoint);
    }

    /**
     * При возникновении ошибки внутри joinPoint ошибка не пробрасывается выше,
     * а записывается в БД
     * @param joinPoint
     * @return
     */
    private Object saveTrack(ProceedingJoinPoint joinPoint) {

        long start = System.currentTimeMillis();
        String method;
        Object result = null;
        try {
            result = joinPoint.proceed();
            method = joinPoint.toString();
        } catch (Throwable e) {
            method = "Exception(" + e + ") from method: " + e.getStackTrace()[0];
        }
        trackService.save(new Track(null, LocalDateTime.now(), method,
                System.currentTimeMillis() - start));
        return result;
    }

    /**
     * При установке обеих аннотаций одновременно будет выброшено исключение
     *
     * @param joinPoint
     * @throws Exception
     */
    private void checkForConflicts(JoinPoint joinPoint) throws Exception {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        boolean hasTrackTime = method.isAnnotationPresent(TrackTime.class);
        boolean hasTrackAsyncTime = method.isAnnotationPresent(TrackAsyncTime.class);

        if (hasTrackTime && hasTrackAsyncTime) {
            throw new IllegalStateException("Method " + method.getName() +
                    " cannot have both @TrackTime and @TrackAsyncTime annotations.");
        }
    }
}
package ru.petrov.util;

import org.springframework.stereotype.Component;
import ru.petrov.annotations.TrackAsyncTime;
import ru.petrov.annotations.TrackTime;

@Component
public class DemoClass {
    @TrackTime
    public void demoMethod(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @TrackAsyncTime
    //    @TrackAsyncTime  //Если установить обе аннотации будет выброшено исключение
    public void demoAsyncMethod(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @TrackTime
    public void demoMethodException(int time) {
        try {
            Thread.sleep(time);
            throw new RuntimeException("some exception from demoMethodException");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

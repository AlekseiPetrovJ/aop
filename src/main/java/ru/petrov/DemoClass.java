package ru.petrov;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
    public void demoAsyncMethod(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

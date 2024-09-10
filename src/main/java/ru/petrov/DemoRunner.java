package ru.petrov;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DemoRunner implements CommandLineRunner {
    private final DemoClass demoClass;

    @Override
    public void run(String... args) throws Exception {
        Stream.generate(() -> (int) (Math.random() * 100 + 100)).limit(20)
                .forEach(t -> demoClass.demoAsyncMethod(t));
        Stream.generate(() -> (int) (Math.random() * 100 + 100)).limit(20)
                .forEach(t -> demoClass.demoMethod(t));
    }
}

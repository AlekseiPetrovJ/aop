package ru.petrov;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.petrov.util.DemoClass;

import java.util.stream.Stream;

@EnableAsync
@RequiredArgsConstructor
@SpringBootApplication
public class ExecutionTrackingApplication {
	private final DemoClass demoClass;
	public static void main(String[] args) {
		SpringApplication.run(ExecutionTrackingApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onReady() {
		Stream.generate(() -> 1000).limit(1)
				.forEach(demoClass::demoMethod);
		Stream.generate(() -> 1000).limit(2)
				.forEach(demoClass::demoAsyncMethod);
		Stream.generate(() -> 1000).limit(1)
				.forEach(demoClass::demoMethod);
		Stream.generate(() -> 100).limit(1)
				.forEach(demoClass::demoMethodException);
	}
}

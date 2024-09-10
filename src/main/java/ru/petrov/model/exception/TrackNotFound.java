package ru.petrov.model.exception;

public class TrackNotFound extends RuntimeException{
    public TrackNotFound() {
    }

    public TrackNotFound(String message) {
        super(message);
    }
}

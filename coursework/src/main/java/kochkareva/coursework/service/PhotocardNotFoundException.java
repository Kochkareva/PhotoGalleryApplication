package kochkareva.coursework.service;

public class PhotocardNotFoundException extends RuntimeException {
    public PhotocardNotFoundException(int id) {
        super(String.format("Photocard with id [%s] is not found", id));
    }
}

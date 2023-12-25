package kochkareva.coursework.service;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super(String.format("User with id [%s] is not found", id));
    }
}

package kochkareva.coursework.service;

public class AlbumNotFoundException  extends RuntimeException {
    public AlbumNotFoundException(int id) {
        super(String.format("Album with id [%s] is not found", id));
    }
}

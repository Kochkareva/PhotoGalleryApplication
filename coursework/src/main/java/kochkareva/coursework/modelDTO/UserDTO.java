package kochkareva.coursework.modelDTO;

import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.model.UserModel;

import java.util.List;

public class UserDTO {
    private Integer id;
    private String login;
    private String password;
    private String status;
    private String description;
    private String role;
    private List<AlbumModel> albums;
    private List<PhotocardModel> photocards;

    public UserDTO() {
    }

    public UserDTO(UserDTO model) {
        this.id = model.getId();
        this.login = model.getLogin();
        this.password = model.getPassword();
        this.status = model.getStatus();
        this.description = model.getDescription();
        this.role = model.getRole();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AlbumModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumModel> albums) {
        this.albums = albums;
    }

    public List<PhotocardModel> getPhotocards() {
        return photocards;
    }

    public void setPhotocards(List<PhotocardModel> photocards) {
        this.photocards = photocards;
    }


    public void addAlbumsToUser(AlbumModel album){
        this.albums.add(album);
    }

    public void addPhotocardsToUser(PhotocardModel photocard){
        this.photocards.add(photocard);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

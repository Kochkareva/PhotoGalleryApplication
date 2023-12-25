package kochkareva.coursework.modelDTO;

import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;

import java.util.List;

public class AlbumDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer id_creator;
    private List<PhotocardModel> photocards;

    public AlbumDTO(){}
    public AlbumDTO(AlbumModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.description = model.getDescription();
        this.id_creator = model.getIdCreator();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdCreator() {
        return id_creator;
    }

    public void setIdCreator(Integer id_creator) {
        this.id_creator = id_creator;
    }

    public List<PhotocardModel> getPhotocards() {
        return photocards;
    }

    public void setPhotocards(List<PhotocardModel> photocards) {
        this.photocards = photocards;
    }

    public void addPhotocardsToAlbum(PhotocardModel photocard){
        this.photocards.add(photocard);
    }
}

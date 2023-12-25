package kochkareva.coursework.modelDTO;

import jakarta.annotation.Nullable;
import jakarta.persistence.Lob;
import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class PhotocardDTO {
    private Integer id;
    private String name;
    private String description;

    private byte[] image;

    private Integer id_album;
    private Integer id_creator;

    public PhotocardDTO(){}
    public PhotocardDTO(PhotocardModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.description = model.getDescription();
        this.id_creator = model.getIdCreator();
        this.id_album = model.getIdAlbum();
        this.image = model.getImage();
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getId_album() {
        return id_album;
    }

    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }

    @Nullable
    public Integer getIdCreator() {
        return id_creator;
    }

    public void setIdCreator(@Nullable Integer id_creator) {
        this.id_creator = id_creator;
    }
}

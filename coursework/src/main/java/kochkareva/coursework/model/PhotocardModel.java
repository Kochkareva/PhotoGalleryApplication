package kochkareva.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="t_photocard")
@Data
public class PhotocardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer id_album;
    private Integer id_creator;
    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] image;

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

    public Integer getIdAlbum() {
        return id_album;
    }

    public void setIdAlbum(Integer id_album) {
        this.id_album = id_album;
    }

    public Integer getIdCreator() {
        return id_creator;
    }

    public void setIdCreator(Integer id_creator) {
        this.id_creator = id_creator;
    }
}

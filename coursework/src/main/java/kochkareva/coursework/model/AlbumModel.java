package kochkareva.coursework.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_album")
@Data
public class AlbumModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer id_creator;

    public AlbumModel() {
    }

    public AlbumModel(AlbumModel albumModel) {
        this.id = albumModel.getId();
        this.name = albumModel.getName();
        this.description = albumModel.getDescription();
        this.id_creator = albumModel.getIdCreator();
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
}

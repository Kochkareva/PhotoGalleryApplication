package kochkareva.coursework.repository;

import kochkareva.coursework.model.AlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumModel, Integer> {

}

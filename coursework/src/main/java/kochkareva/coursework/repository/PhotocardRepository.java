package kochkareva.coursework.repository;

import kochkareva.coursework.model.PhotocardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotocardRepository extends JpaRepository<PhotocardModel, Integer> {
}

package kochkareva.coursework.service;

import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.modelDTO.PhotocardDTO;
import kochkareva.coursework.repository.PhotocardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotocardService {
    private final PhotocardRepository photocardRepository;
    public PhotocardService(PhotocardRepository photocardRepository) {
        this.photocardRepository = photocardRepository;
    }

    public List<PhotocardModel> findAllPhotocard() throws Exception {
        return photocardRepository.findAll();
    }

    public PhotocardModel findById(int id) throws Exception {
        final Optional<PhotocardModel> album = photocardRepository.findById(id);
        return album.orElseThrow(() -> new PhotocardNotFoundException(id));
    }

    public PhotocardModel addPhotocard(PhotocardDTO photocardDTO) {
        PhotocardModel photocard = new PhotocardModel();
        photocard.setName(photocardDTO.getName());
        photocard.setDescription(photocardDTO.getDescription());
        photocard.setImage(photocardDTO.getImage());
        photocard.setIdCreator(photocardDTO.getIdCreator());
        photocard.setIdAlbum(photocardDTO.getId_album());
        return photocardRepository.save(photocard);
    }

    public PhotocardModel updatePhotocard(PhotocardDTO photocardDTO){
        PhotocardModel photocard = photocardRepository.findById(photocardDTO.getId()).orElseThrow();
        photocard.setId(photocard.getId());
        photocard.setName(photocardDTO.getName());
        photocard.setDescription(photocardDTO.getDescription());
        photocard.setImage(photocardDTO.getImage());
        photocard.setIdCreator(photocardDTO.getIdCreator());
        photocard.setIdAlbum(photocardDTO.getId_album());
        return photocardRepository.save(photocard);
    }

    public void deletePhotocard(int id){
        photocardRepository.delete(photocardRepository.findById(id).orElseThrow());
    }

    public List<PhotocardModel> findPhotocardsByAlbum(int idAlbum) {
        return photocardRepository.findAll().stream()
                .filter(photocard -> photocard.getIdAlbum() != null)
                .filter(photocard -> photocard.getIdAlbum() == idAlbum)
                .toList();
    }

    public List<PhotocardModel> findPhotocardsByUser(int idUser) {
        return photocardRepository.findAll().stream()
                .filter(photocard -> photocard.getIdCreator() != null)
                .filter(photocard -> photocard.getIdCreator() == idUser)
                .toList();
    }
}

package kochkareva.coursework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.model.UserModel;
import kochkareva.coursework.modelDTO.AlbumDTO;
import kochkareva.coursework.modelDTO.PhotocardDTO;
import kochkareva.coursework.modelDTO.UserDTO;
import kochkareva.coursework.repository.AlbumRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final PhotocardService photocardService;
    public AlbumService(AlbumRepository albumRepository, PhotocardService photocardService) {
        this.albumRepository = albumRepository;
        this.photocardService = photocardService;
    }

    public List<AlbumDTO> findAllAlbum() throws Exception {
        List<AlbumDTO> albums = new ArrayList<>();
        for(AlbumModel album : albumRepository.findAll()){
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setId(album.getId());
            albumDTO.setName(album.getName());
            albumDTO.setDescription(album.getDescription());
            albumDTO.setIdCreator(album.getIdCreator());
            List<PhotocardModel> photocards = photocardService.findPhotocardsByAlbum(album.getId());
            if(!photocards.isEmpty()){
                albumDTO.setPhotocards(new ArrayList<>());
                for(PhotocardModel photocard : photocards){
                    albumDTO.addPhotocardsToAlbum(photocard);
                }
            }
            albums.add(albumDTO);
        }
        return albums;
    }

    public AlbumDTO findById(int id) throws Exception {
        AlbumDTO albumDTO = new AlbumDTO();
        AlbumModel album = albumRepository.findById(id).orElseThrow();
        albumDTO.setId(album.getId());
        albumDTO.setName(album.getName());
        albumDTO.setDescription(album.getDescription());
        albumDTO.setIdCreator(album.getIdCreator());
        List<PhotocardModel> photocards = photocardService.findPhotocardsByAlbum(album.getId());
        if(!photocards.isEmpty()){
            albumDTO.setPhotocards(new ArrayList<>());
            for(PhotocardModel photocard : photocards){
                albumDTO.addPhotocardsToAlbum(photocard);
            }
        }
        return albumDTO;
    }

    public AlbumModel addAlbum(AlbumDTO albumDTO) {
        AlbumModel album = new AlbumModel();
        album.setName(albumDTO.getName());
        album.setDescription(albumDTO.getDescription());
        album.setIdCreator(albumDTO.getIdCreator());
        return albumRepository.save(album);
    }

    public AlbumModel updateAlbum(AlbumDTO albumDTO){
        AlbumModel album = albumRepository.findById(albumDTO.getId()).orElseThrow();
        album.setId(album.getId());
        album.setName(albumDTO.getName());
        album.setDescription(albumDTO.getDescription());
        album.setIdCreator(albumDTO.getIdCreator());
        return albumRepository.save(album);
    }

    public void deleteAlbum(int id){
        albumRepository.delete(albumRepository.findById(id).orElseThrow());
    }

    public List<AlbumModel> findAlbumsByUser(int idCreator) {
        return albumRepository.findAll().stream()
                .filter(album -> album.getIdCreator() != null)
                .filter(album -> album.getIdCreator() == idCreator)
                .toList();
    }

    /*
    private String name;
    private String description;
    private Integer id_album;
    private Integer id_creator;
    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] image;
     */
    public AlbumDTO addPhotocardToAlbumDTO(AlbumDTO albumDTO, PhotocardDTO photocardDTO){
        PhotocardModel photocardModel = new PhotocardModel();
        photocardModel.setName(photocardDTO.getName());
        photocardModel.setDescription(photocardDTO.getDescription());
        photocardModel.setIdCreator(photocardDTO.getIdCreator());
        photocardModel.setImage(photocardDTO.getImage());
        albumDTO.addPhotocardsToAlbum(photocardModel);
        return albumDTO;
    }
}

package kochkareva.coursework.controller;

import jakarta.validation.Valid;
import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.modelDTO.AlbumDTO;
import kochkareva.coursework.modelDTO.PhotocardDTO;
import kochkareva.coursework.service.AlbumService;
import kochkareva.coursework.service.PhotocardService;
import kochkareva.coursework.service.SessionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/album")
public class AlbumMvcController {
    private final AlbumService albumService;
    private final PhotocardService photocardService;
    private SessionService sessionService;
    private String imageBytes;
    public AlbumMvcController(AlbumService albumService, PhotocardService photocardService,
                              SessionService sessionService) {
        this.albumService = albumService;
        this.photocardService = photocardService;
        this.sessionService = sessionService;
    }

    @GetMapping("/userAlbums")
    public String getDepartments(Model model) throws Exception {
        model.addAttribute("albums",
                albumService.findAlbumsByUser(sessionService.getIdUser()).stream()
                        .map(AlbumDTO::new)
                        .collect(Collectors.toList()));
        return "userAlbums";
    }

    @GetMapping("/getUserAlbums")
    public ResponseEntity<Model> getUserAlbums(@ModelAttribute("albumDTO") @Valid AlbumModel albumDTO,
                                                  Model model) throws Exception {
        System.out.println(sessionService.getIdUser());
        model.addAttribute("albumDTO",
                albumService.findAlbumsByUser(sessionService.getIdUser()).stream()
                        .map(AlbumModel::new)
                        .collect(Collectors.toList()));
        return ResponseEntity.ok(model);
        // return "userAlbums";
    }

    @PostMapping("/getUserAlbums")
    public ResponseEntity<Model> postUserAlbums(@ModelAttribute("albumDTO") @Valid AlbumModel albumDTO,
                                               Model model) throws Exception {

        return ResponseEntity.ok(model);
        // return "userAlbums";
    }

    @GetMapping(value = "/albumImage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAlbumImage(@PathVariable("id") String id) throws Exception {
        // Ваш код для создания изображения на основе id
        AlbumDTO albums = albumService.findById(Integer.parseInt(id));
        byte[] imageBytes = photocardService.findById(albums.getPhotocards().get(0).getId()).getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }


    @GetMapping(value = "/create")
    public String createAlbum(
            @ModelAttribute("albumDTO") @Valid AlbumDTO albumDTO,
            BindingResult bindingResult,
            Model model) {
        AlbumDTO album = new AlbumDTO();
        model.addAttribute("albumDTO", album);
        /*PhotocardDTO photocard = new PhotocardDTO();
        photocard.setImage(new byte[0]);
        model.addAttribute("photocardDTO", photocard);
        model.addAttribute("albumDTO", album);*/
        return "createAlbum";
    }


    @PostMapping(value = "/create", consumes="multipart/form-data")
    public String saveAlbum(
            @ModelAttribute("albumDTO") @Valid AlbumDTO albumDTO,
            BindingResult bindingResult,
            Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println(bindingResult.getAllErrors());
            return "createAlbum";
        }
        albumDTO.setIdCreator(sessionService.getIdUser());
        AlbumModel modelAlbum = albumService.addAlbum(albumDTO);
        /*if(!albumDTO.getPhotocards().isEmpty()) {
            for(PhotocardModel photocard : albumDTO.getPhotocards()) {
                if(photocard.getId() == null) {
                    PhotocardDTO photocardDTO = new PhotocardDTO();
                    photocardDTO.setName(photocard.getName());
                    photocardDTO.setDescription(photocard.getDescription());
                    photocardDTO.setIdCreator(photocard.getIdCreator());
                    photocardDTO.setImage(photocard.getImage());
                    photocardDTO.setIdAlbum(modelAlbum.getId());
                    photocardService.addPhotocard(photocardDTO);
                }
            }
        }*/
        return "redirect:/album/userAlbums";
    }
}

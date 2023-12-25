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
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/photocard")
public class PhotocardMvcController {
    private final PhotocardService photocardService;
    private final AlbumService albumService;
    private SessionService sessionService;
    private String imageBytes;
    public PhotocardMvcController(PhotocardService photocardService, SessionService sessionService,
                                  AlbumService albumService) {
        this.photocardService = photocardService;
        this.sessionService = sessionService;
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public String getDepartments(Model model) throws Exception {
        model.addAttribute("photocards",
                photocardService.findAllPhotocard().stream()
                        .map(PhotocardDTO::new)
                        .collect(Collectors.toList()));
        return "userPhotocards";
    }


    @GetMapping(value = "/create")
    public String createPhotocard(
            @ModelAttribute("photocardDTO") @Valid PhotocardDTO photocardDTO,
            @ModelAttribute("albumDTO") @Valid AlbumModel albumDTO,
            BindingResult bindingResult,
            Model model) {
        PhotocardDTO photocard = new PhotocardDTO();
        photocard.setImage(new byte[0]);

        model.addAttribute("albumDTO",
                albumService.findAlbumsByUser(sessionService.getIdUser()).stream()
                        .map(AlbumModel::new)
                        .collect(Collectors.toList()));
        model.addAttribute("photocardDTO", photocard);
        model.addAttribute("photocardDTO", photocard);
        //return ResponseEntity.ok(photocardDTO);
        return "createPhotocard";
    }
    /*
    @GetMapping(value = "/create")
    public ResponseEntity<PhotocardDTO> createPhotocard(
            @ModelAttribute("photocardDTO") @Valid PhotocardDTO photocardDTO,
            BindingResult bindingResult,
            Model model) {
        PhotocardDTO photocard = new PhotocardDTO();
        photocard.setImage(new byte[0]);
        model.addAttribute("photocardDTO", photocard);
        return ResponseEntity.ok(photocard);
        //return "createPhotocard";
    }

    @PostMapping(value = "/create", consumes="multipart/form-data")
    public ResponseEntity<PhotocardDTO> savePhotocard(
            @ModelAttribute("photocardDTO") @Valid PhotocardDTO photocardDTO,
            @ModelAttribute("albumDTO") @Valid AlbumDTO albumDTO,
            @RequestParam("imageFile") MultipartFile image,
            BindingResult bindingResult,
            Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println(bindingResult.getAllErrors());

        }
        //byte[] imageBytes = image.getBytes();
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        photocardDTO.setImage(imageBytes);
        photocardDTO.setIdCreator(sessionService.getIdUser());
        photocardDTO.setIdAlbum(1);
        albumService.addPhotocardToAlbumDTO(albumDTO, photocardDTO);
        model.addAttribute("photocardDTO", photocardDTO);
        model.addAttribute("albumDTO", albumDTO);
        return ResponseEntity.ok(photocardDTO);
    }*/
    @PostMapping(value = "/create", consumes="multipart/form-data")
    public String savePhotocard(
                                @ModelAttribute @Valid PhotocardDTO photocardDTO,
                                @RequestParam("imageFile") MultipartFile image,
                                BindingResult bindingResult,
                                Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println(bindingResult.getAllErrors());
            return "createPhotocard";
        }
        //byte[] imageBytes = image.getBytes();
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        photocardDTO.setImage(imageBytes);
        photocardDTO.setIdCreator(sessionService.getIdUser());
       // photocardDTO.setIdAlbum(1);
        photocardService.addPhotocard(photocardDTO);
        return "redirect:/photocard/all";
    }

   /* @GetMapping("/image/{id}")
    public String getImage(@PathVariable("id") int id) throws Exception {
        // Ваш код для создания изображения на основе id
        byte[] imageBytes = photocardService.findById(id).getImage(); // Ваши байты изображения

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return base64Image;
    }*/
    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws Exception {
        // Ваш код для создания изображения на основе id
        byte[] imageBytes = photocardService.findById(Integer.parseInt(id)).getImage();// Ваши байты изображения

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}

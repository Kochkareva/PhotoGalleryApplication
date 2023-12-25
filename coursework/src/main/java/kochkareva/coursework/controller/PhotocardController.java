package kochkareva.coursework.controller;

import jakarta.validation.Valid;
import kochkareva.coursework.config.WebConfiguration;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.modelDTO.PhotocardDTO;
import kochkareva.coursework.modelDTO.UserDTO;
import kochkareva.coursework.service.PhotocardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping(WebConfiguration.REST_API +"/photocard")
public class PhotocardController {
    private final PhotocardService photocardService;

    public PhotocardController(PhotocardService photocardService){
        this.photocardService = photocardService;
    }

    @GetMapping("/all")
    public List<PhotocardModel> getAllPhotocard() throws Exception {
        return photocardService.findAllPhotocard();
    }
    @GetMapping("/{id}")
    public PhotocardDTO getPhotocard(@PathVariable int id) throws Exception {
        return new PhotocardDTO(photocardService.findById(id));
    }

    @PostMapping("/")
    public PhotocardDTO createPhotocard(@RequestBody @Valid PhotocardDTO photocardDTO) {
        return new PhotocardDTO(photocardService.addPhotocard(photocardDTO));
    }


    @PutMapping("/updatePhotocard")
    public PhotocardDTO updatePhotocard(
                                  @RequestBody @Valid PhotocardDTO photocardDTO) {
        return new PhotocardDTO(photocardService.updatePhotocard(photocardDTO));
    }


    @DeleteMapping("/{id}")
    public void deletePhotocard(@PathVariable int id) {
        photocardService.deletePhotocard(id);
    }
}

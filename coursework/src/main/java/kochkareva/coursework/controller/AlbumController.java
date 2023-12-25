package kochkareva.coursework.controller;

import jakarta.validation.Valid;
import kochkareva.coursework.config.WebConfiguration;
import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.modelDTO.AlbumDTO;
import kochkareva.coursework.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping(WebConfiguration.REST_API +"/album")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public List<AlbumDTO> getAllAlbum() throws Exception {
        return albumService.findAllAlbum();
    }

    @GetMapping("/{id}")
    public AlbumDTO getAlbum(@PathVariable int id) throws Exception {
        return albumService.findById(id);
    }

    @PostMapping("/")
    public AlbumDTO createAlbum(@RequestBody @Valid AlbumDTO albumDTO) {
        return new AlbumDTO(albumService.addAlbum(albumDTO));
    }


    @PutMapping("/updateLesson")
    public AlbumDTO updateLesson(
                                  @RequestBody @Valid AlbumDTO albumDTO) {
        return new AlbumDTO(albumService.updateAlbum(albumDTO));
    }


    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable int id) {
        albumService.deleteAlbum(id);
    }
}

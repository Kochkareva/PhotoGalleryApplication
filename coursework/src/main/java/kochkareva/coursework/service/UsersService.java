package kochkareva.coursework.service;

import kochkareva.coursework.model.AlbumModel;
import kochkareva.coursework.model.PhotocardModel;
import kochkareva.coursework.model.UserModel;
import kochkareva.coursework.modelDTO.UserDTO;
import kochkareva.coursework.repository.AlbumRepository;
import kochkareva.coursework.repository.PhotocardRepository;
import kochkareva.coursework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final PhotocardRepository photocardRepository;
    private final AlbumService albumService;
    private final PhotocardService photocardService;
    public UsersService(UserRepository userRepository, AlbumRepository albumRepository,
                        PhotocardRepository photocardRepository, AlbumService albumService,
                        PhotocardService photocardService) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.photocardRepository = photocardRepository;
        this.albumService = albumService;
        this.photocardService = photocardService;
    }

    public List<UserDTO> findAllUser() throws Exception {
        List<UserDTO> users = new ArrayList<>();
        for(UserModel user : userRepository.findAll()){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getLogin());
            userDTO.setPassword(user.getPassword());
            userDTO.setStatus(user.getStatus());
            userDTO.setRole(user.getRole());
            userDTO.setDescription(user.getDescription());
            List<AlbumModel> albums = albumService.findAlbumsByUser(user.getId());
            if(!albums.isEmpty()){
                userDTO.setAlbums(new ArrayList<>());
                for(AlbumModel album : albums){
                    userDTO.addAlbumsToUser(album);
                }
            }
            users.add(userDTO);

            List<PhotocardModel> photocards = photocardService.findPhotocardsByUser(user.getId());
            if(!photocards.isEmpty()){
                userDTO.setPhotocards(new ArrayList<>());
                for (PhotocardModel photocard : photocards){
                    userDTO.addPhotocardsToUser(photocard);
                }
            }
        }
        return users;
    }

    public UserDTO findById(int id) throws Exception {
        UserDTO userDto = new UserDTO();
        UserModel user= userRepository.findById(id).orElseThrow();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        userDto.setDescription(user.getDescription());
        List<AlbumModel> albums = albumService.findAlbumsByUser(user.getId());
        if(!albums.isEmpty()){
            userDto.setAlbums(new ArrayList<>());
            for(AlbumModel album : albums){
                userDto.addAlbumsToUser(album);
            }
        }
        List<PhotocardModel> photocards = photocardService.findPhotocardsByUser(user.getId());
        if(!photocards.isEmpty()){
            userDto.setPhotocards(new ArrayList<>());
            for (PhotocardModel photocard : photocards){
                userDto.addPhotocardsToUser(photocard);
            }
        }
        return userDto;
    }

    public UserModel addUser(UserDTO userDTO) {
        UserModel user = new UserModel();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        user.setDescription(userDTO.getDescription());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public UserModel updateUser(UserDTO userDTO){
        UserModel user = userRepository.findById(userDTO.getId()).orElseThrow();
        user.setId(user.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        user.setDescription(userDTO.getDescription());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    public UserModel authorization(String username, String password) {
        return userRepository.findAll().stream()
                .filter(user -> user.getLogin() != null && user.getLogin().equals(username))
                .filter(user -> user.getPassword() != null && user.getPassword().equals(password))
                .filter(user -> user.getStatus() != null) // Добавленная проверка на null для значения "status"
                .findFirst()
                .orElse(null);
    }
}

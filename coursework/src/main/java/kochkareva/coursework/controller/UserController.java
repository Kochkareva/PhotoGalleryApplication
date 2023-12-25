package kochkareva.coursework.controller;

import jakarta.validation.Valid;
import kochkareva.coursework.config.WebConfiguration;
import kochkareva.coursework.model.UserModel;
import kochkareva.coursework.modelDTO.UserDTO;
import kochkareva.coursework.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(WebConfiguration.REST_API +"/user")
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUser() throws Exception {
        return usersService.findAllUser();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) throws Exception {
        return new UserDTO(usersService.findById(id));
    }

    @PostMapping("/")
    public UserModel createUser(@RequestBody @Valid UserDTO userDTO) {
        return usersService.addUser(userDTO);
    }

    @PutMapping("/updateUsers")
    public UserModel updateUser(@RequestBody @Valid UserDTO userDTO) {
        return usersService.updateUser(userDTO);
    }

/*
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

 */
}

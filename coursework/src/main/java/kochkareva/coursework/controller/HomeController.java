package kochkareva.coursework.controller;

import jakarta.validation.Valid;
import kochkareva.coursework.model.UserModel;
import kochkareva.coursework.modelDTO.PhotocardDTO;
import kochkareva.coursework.modelDTO.UserDTO;

import kochkareva.coursework.service.PhotocardService;
import kochkareva.coursework.service.SessionService;
import kochkareva.coursework.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    private UsersService userService;
    private SessionService sessionService;
  //  private UserDetailsServiceImpl userDetailsService;

    public HomeController(UsersService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }
    @PostMapping(value = "/register")
    public String registerUser(
            @ModelAttribute @Valid UserDTO userDTO,
            BindingResult bindingResult,
            Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println(bindingResult.getAllErrors());
            return "register";
        }
        UserModel user = userService.addUser(userDTO);
        sessionService.addToSession(user);
        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public String register(
            @ModelAttribute @Valid UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("userDTO", user);
        return "register";
    }


    @PostMapping(value = "/login")
    public String loginUser(
            @ModelAttribute @Valid UserDTO userDTO,
            BindingResult bindingResult,
            Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println(bindingResult.getAllErrors());
            return "login";
        }
        UserModel user = userService.authorization(userDTO.getLogin(), userDTO.getPassword());
        sessionService.addToSession(user);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(
            @ModelAttribute @Valid UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("userDTO", user);
        return "login";
    }
}

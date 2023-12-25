package kochkareva.coursework.service;

import jakarta.servlet.http.HttpSession;
import kochkareva.coursework.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionService {

    private final HttpServletRequest request;

    public SessionService(HttpServletRequest request) {
        this.request = request;
    }

    public void addToSession(UserModel user) {
        HttpSession session = getSession();
        session.setAttribute("idUser", user.getId());
        session.setAttribute("loginUser", user.getLogin());
        session.setAttribute("passwordUser", user.getPassword());
        session.setAttribute("statusUser", user.getStatus());
        session.setAttribute("roleUser", user.getRole());
        session.setAttribute("descriptionUser", user.getDescription());
    }
    private HttpSession getSession(){
       return request.getSession();
    }

    public int getIdUser(){
        HttpSession session = getSession();
        return (int) session.getAttribute("idUser");
    }

    public String getLogin(){
        HttpSession session = getSession();
        return (String) session.getAttribute("loginUser");
    }

    public String getPassword(){
        HttpSession session = getSession();
        return (String) session.getAttribute("passwordUser");
    }

    public String getStatus(){
        HttpSession session = getSession();
        return (String) session.getAttribute("statusUser");
    }

    public String getRole(){
        HttpSession session = getSession();
        return (String) session.getAttribute("roleUser");
    }

    public String getDescription(){
        HttpSession session = getSession();
        return (String) session.getAttribute("descriptionUser");
    }
}

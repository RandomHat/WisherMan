package com.example.wisherman.controller;

import com.example.wisherman.model.User;
import com.example.wisherman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.jar.Attributes;

@Controller
public class AccountController {
    UserService userService = new UserService();

    @GetMapping("/new-account")
    public String newAccountView() {
        return "new-account";
    }

    @PostMapping("/new-account")
    public String createAccount(WebRequest requestFromUser) {
        User currentUser = userService.makeUser(
        requestFromUser.getParameter("username"),
        requestFromUser.getParameter("password"),
        requestFromUser.getParameter("firstName"),
        requestFromUser.getParameter("lastName"),
        requestFromUser.getParameter("email")
        );
        userService.saveUser(currentUser);
        System.out.println(currentUser.toString());
        //TODO få lavet service til database UserService.addToDB(currentUser);
        return "redirect:/";

    }

    @GetMapping("/hp")
    public String test() {
        return "Template";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
    @PostMapping("/login")
    public String login(){
        return "Template"; //TODO få lavet flashAttribute / Session attribute
    }
}


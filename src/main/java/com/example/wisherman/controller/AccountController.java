package com.example.wisherman.controller;

import com.example.wisherman.model.User;
import com.example.wisherman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.servlet.http.HttpSession;

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
        if(userService.userIsValid(currentUser)) {
            userService.saveUser(currentUser);
            return"redirect:/";
        }else{
            System.out.println("error User not created"); //TODO handle redirect ved forkert bruger input?
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, WebRequest requestFromUser){
        int id = userService.loginApproved(
                requestFromUser.getParameter("username"),
                requestFromUser.getParameter("password"));
        if(id > 0){
            session.setAttribute("user", userService.approvedUser(id));
            return "redirect:/user-panel";
        } else
            return "login";
    }

    @GetMapping ("/user-panel")
    public String login(HttpSession session){

        return "show-user-wishlists";
    }
}


package com.example.wisherman.controller;

import com.example.wisherman.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.jar.Attributes;

@Controller
public class AccountController {

    @GetMapping("/new_account")
    public String newAccountView(){
        return "new-account";
    }

    @PostMapping("/new_account")
    public String createAccount(WebRequest requestFromUser){
        String username = requestFromUser.getParameter("username");
        String password = requestFromUser.getParameter("password");
        String firstName = requestFromUser.getParameter("firstName");
        String lastName = requestFromUser.getParameter("lastName");
        String email = requestFromUser.getParameter("email");
        User currentUser = new User(username,password,firstName,lastName,email);
        System.out.println(currentUser.toString());
        //UserService.addToDB(currentUser);
    return "redirect:/";



    }
}

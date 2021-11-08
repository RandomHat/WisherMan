package com.example.wisherman.controller;

import com.example.wisherman.utlility.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

}

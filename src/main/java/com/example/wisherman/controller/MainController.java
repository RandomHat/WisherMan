package com.example.wisherman.controller;

import com.example.wisherman.model.Wish;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;
import com.example.wisherman.service.WishService;
import com.example.wisherman.utlility.DBConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }
}
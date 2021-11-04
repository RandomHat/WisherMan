package com.example.wisherman.controller;

import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WishListController {

    private WishListRepository wishlistrepository;

    public WishListController() {
        wishlistrepository = new WishListRepository();
    }

    @GetMapping("/wishlist/show-all-wishlists")
    public String showAllWishlists(Model model)   {
        List<WishList> wishLists = wishlistrepository.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "show-all-wishlists";
    }
}

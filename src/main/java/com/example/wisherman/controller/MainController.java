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
    private WishListRepository wishListRepository = new WishListRepository();
    private WishService wishService = new WishService();

    @GetMapping("/")
    public String getHome() {
        return "index";
    }


    @GetMapping("/wishlist/share-wishlists")
    public String shareWishlists(@RequestParam (defaultValue = "0") String user, Model model) {
        int userId = Integer.parseInt(user);
        if (userId == 0) {
            return "redirect:/wishlist/show-all-wishlists";
        } else {
            List<WishList> wishListList = wishListRepository.getUserWishLists(userId); // ændres til service
            model.addAttribute("wishListList", wishListList);
            return "share-user-wishlists";
        }
    }

    @GetMapping("wishlist/share-wishlist")
    public String shareWishlist(@RequestParam (defaultValue = "0") String wishlist, Model model ){
        int wishlistId = Integer.parseInt(wishlist);

        if (wishlistId <= 0){
            return "redirect:/wishlist/show-all-wishlists";
        } else{
            List<Wish> wishList = wishService.getWishListWishes(wishlistId);
            model.addAttribute("wishlistWishes",wishList);
            return "share-wishlist-wishes";
        }
    }
}
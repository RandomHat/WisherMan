package com.example.wisherman.service;

import com.example.wisherman.model.Wish;
import com.example.wisherman.repositories.WishRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class WishService {
    WishRepository wishes = new WishRepository();

    public List<Wish> getWishListWishes(HttpSession session){
        int wishListID = (int) session.getAttribute("wishListID");
        return wishes.getWishListWishes(wishListID);
    }
}

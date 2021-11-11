package com.example.wisherman.service;

import com.example.wisherman.model.Wish;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class WishService {
    WishRepository wishes = new WishRepository();

    public List<Wish> getWishListWishes(HttpSession session){
        WishList wishList = (WishList) session.getAttribute("wishlist");
        return wishes.getWishListWishes(wishList.getIdwishlist());
    }

    public List<Wish> getWishListWishes(int listID){
        return wishes.getWishListWishes(listID);
    }

    public boolean addWishToDB(Wish wish) {
        return wishes.addWishToWishList(wish);
    }

    public List<Wish> getAllWishes(){
        return wishes.getAllWishes();
    }
}

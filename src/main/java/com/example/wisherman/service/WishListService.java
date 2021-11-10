package com.example.wisherman.service;

import com.example.wisherman.model.User;
import com.example.wisherman.model.Wish;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;

import java.util.*;
import javax.servlet.http.HttpSession;

public class WishListService {

    WishListRepository wishLists = new WishListRepository();

    public List<WishList> getWishLists(HttpSession session){
        User user = (User) session.getAttribute("user");
        return wishLists.getUserWishLists(user.getUserID());
    }
    public WishList getWishList(int listId){

        return wishLists.getWishList(listId);
    }


}

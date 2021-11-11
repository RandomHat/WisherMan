package com.example.wisherman.service;

import com.example.wisherman.model.User;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;

import java.util.*;
import javax.servlet.http.HttpSession;

public class WishListService {

    WishListRepository wishLists = new WishListRepository();
    UserService user = new UserService();

    public List<WishList> getWishLists(HttpSession session){
        User user = (User) session.getAttribute("user");
        return wishLists.getUserWishLists(user.getUserID());
    }

    public List<WishList> getUserSessionWishLists(HttpSession session){
        return wishLists.getUserWishLists(user.getUserSessionID(session));
    }

    public List<WishList> getUserIdWishLists(int id){
        return wishLists.getUserWishLists(id);
    }

    public List<WishList> getAllWishlists (){
        return wishLists.getAllWishLists();
    }

    public WishList getWishList(int listId){
        return wishLists.getWishList(listId);
    }

    public boolean addWishListToDB(WishList wishList){
        return wishLists.addWishListToDB(wishList);
    }

}

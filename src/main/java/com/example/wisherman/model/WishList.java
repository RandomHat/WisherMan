package com.example.wisherman.model;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;

public class WishList {
    private String listName;
    private ArrayList<Wish> wishes;

    public WishList(String listName) {
        this.listName = listName;
    }

    public WishList(){};

    public void addWish(Wish wishToAdd){
        wishes.add(wishToAdd);
    }

    public ArrayList<Wish> getWishes() {
        return wishes;
    }

    public void deleteWish(Wish wishToRemove){
        for(Wish wish : wishes){
            if(wishToRemove.equals(wish)){
                wishes.remove(wish);
                break;
            }
        }
    }

    public boolean isValidWishList(WishList wishList) {
        return wishList != null && Strings.isNotBlank(wishList.getListName());
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}

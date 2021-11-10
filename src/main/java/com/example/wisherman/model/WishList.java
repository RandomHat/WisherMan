package com.example.wisherman.model;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;

public class WishList {
    private int idwishlist;
    private String listName;
    private int userid;
    private ArrayList<Wish> wishes;

    public WishList(int idwishlist, String listName, int userid) {
        this.idwishlist = idwishlist;
        this.listName = listName;
        this.userid = userid;
    }

    public WishList(){}

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

    public int getIdwishlist() {
        return idwishlist;
    }

    public boolean isValidWishList(WishList wishList) {
        return wishList != null && Strings.isNotBlank(wishList.getListName());
    }

    public String getListName() {
        return listName;
    }

    public int getUserid() {
        return userid;
    }

    public void setIdwishlist(int idwishlist) {
        this.idwishlist = idwishlist;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}

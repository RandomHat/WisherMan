package com.example.wisherman.model;

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

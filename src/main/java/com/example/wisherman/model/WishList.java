package com.example.wisherman.model;

import org.apache.logging.log4j.util.Strings;

public class WishList {
    private int idwishlist;
    private String listName;
    private int userid;

    public WishList(int idwishlist, String listName, int userid) {
        this.idwishlist = idwishlist;
        this.listName = listName;
        this.userid = userid;
    }

    public WishList(){}

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

    @Override
    public String toString() {
        return "WishList{" +
                "idwishlist=" + idwishlist +
                ", listName='" + listName + '\'' +
                ", userid=" + userid +
                '}';
    }
}


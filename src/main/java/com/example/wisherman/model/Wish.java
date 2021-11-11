package com.example.wisherman.model;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Wish {
    private int id;
    private String title;
    private String url;
    private String price;
    private boolean reserved = false;
    private int wishListID;

    public Wish(int id, String title, String url, String price, boolean reserved, int wishListID) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.price = price;
        this.reserved = reserved;
        this.wishListID = wishListID;
        System.out.println("Wish Constructor. Wish: " + this.title + " ID: " + this.id + " Constructed");
    }

    public Wish(){
        System.out.println("in empty wish constructor");
    }

    //testet virker nu
    public boolean isValidWish(Wish wish){
        return wish != null && Strings.isNotBlank(wish.getTitle())
                && Strings.isNotBlank(wish.getUrl())
                && Strings.isNotBlank(wish.getPrice())
                && wish.getWishListID() != 0;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "title='" + title + '\'' +
                ", link='" + url + '\'' +
                ", price='" + price + '\'' +
                ", reserved=" + reserved +
                ", wishlistid=" + wishListID + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return title.equals(wish.title) && url.equals(wish.url) && Objects.equals(price, wish.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, price);
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public boolean isReservedBool() {
        return reserved;
    }

    public int isReservedInt() {
        if (!reserved){
            return 0;
        }
        return 1;
    }

    public String getPrice() {
        return price;
    }

    public int getWishListID() {
        return wishListID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

}

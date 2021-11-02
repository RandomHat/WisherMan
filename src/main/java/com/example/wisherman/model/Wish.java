package com.example.wisherman.model;

import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Wish {
    private String title;
    private String price;
    private String url;
    private boolean reserved = false;

    public Wish(String title, String link, String price) {
        this.title = title;
        this.url = link;
        this.price = price;
        System.out.println("in Wish Constructor");
    }

    public Wish(){
        System.out.println("in empty wish constructor");
    }

    //testet virker nu
    public boolean isValidWish(Wish wish){
        return wish != null && Strings.isNotBlank(wish.getTitle())
                && Strings.isNotBlank(wish.getUrl())
                && Strings.isNotBlank(wish.getPrice());
    }

    @Override
    public String toString() {
        return "Wish{" +
                "title='" + title + '\'' +
                ", link='" + url + '\'' +
                ", price='" + price + '\'' +
                ", reserved=" + reserved +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

}

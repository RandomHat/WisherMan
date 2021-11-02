package com.example.wisherman.model;

public class Wish {
    private String title;
    private String link;
    private String price;
    private boolean reserved;

    public Wish(String title, String link, String price, boolean reserved) {
        this.title = title;
        this.link = link;
        this.price = price;
        this.reserved = reserved;
    }

    public Wish(){}

    @Override
    public String toString() {
        return "Wish{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", price='" + price + '\'' +
                ", reserved=" + reserved +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

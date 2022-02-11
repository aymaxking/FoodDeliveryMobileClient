package com.example.fooddeliverymobileclient.Domain;

public class SubMenu {
    Long id;
    String title;
    double price;
    byte[] img;


    public SubMenu(Long id, String title, double price,byte[] img) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.img=img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

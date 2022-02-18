package com.example.fooddeliverymobileclient.Domain;

import java.util.ArrayList;

public class Place extends User{
    String title;
    String description;
    String location;
    ArrayList<Menu> menus ;

    public Place(Long id, String username, String password, String role,String number,byte[] img, String title, String description,String location, ArrayList<Menu> menus) {
        super(id, username, password, role,number,img);
        this.title = title;
        this.description = description;
        this.menus = menus;
        this.location=location;
    }

    public Place(Long id, String username, String password, String role,String number,byte[] img, String title, String description,String location) {
        super(id, username, password, role,number,img);
        this.title = title;
        this.description = description;
        this.location=location;

    }

    public byte[] getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }
}

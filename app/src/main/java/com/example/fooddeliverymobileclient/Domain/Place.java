package com.example.fooddeliverymobileclient.Domain;

import java.util.ArrayList;

public class Place extends User{
    String title;
    String description;
    ArrayList<Menu> menus ;

    public Place(Long id, String username, String password, String role, String title, String description, ArrayList<Menu> menus) {
        super(id, username, password, role);
        this.title = title;
        this.description = description;
        this.menus = menus;
    }

    public Place(Long id, String username, String password, String role, String title, String description) {
        super(id, username, password, role);
        this.title = title;
        this.description = description;
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

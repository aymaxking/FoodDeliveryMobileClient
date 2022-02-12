package com.example.fooddeliverymobileclient.Domain;

import java.util.ArrayList;

public class Menu {
    Long id;

    String title;

    ArrayList<SubMenu> subMenus = new ArrayList<SubMenu>();

    public Menu(Long id, String title, ArrayList<SubMenu> subMenus) {
        this.id = id;
        this.title = title;
        this.subMenus = subMenus;
    }

    public Menu(Long id, String title) {
        this.id = id;
        this.title = title;
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

    public ArrayList<SubMenu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(ArrayList<SubMenu> subMenus) {
        this.subMenus = subMenus;
    }
}

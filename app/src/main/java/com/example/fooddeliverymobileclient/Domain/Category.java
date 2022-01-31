package com.example.fooddeliverymobileclient.Domain;

import java.util.ArrayList;
import java.util.List;

public class Category{
    private Long id;
    private String title;
    private ArrayList<Type> types;

    public Category(Long id, String title, ArrayList<Type> types) {
        this.id = id;
        this.title = title;
        this.types = types;
    }
    public Category(String title, ArrayList<Type> types) {
        this.title = title;
        this.types = types;
    }
    public Category(String title) {
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

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }
}

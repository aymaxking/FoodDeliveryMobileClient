package com.example.fooddeliverymobileclient.Domain;

public class Type {
    private Long id;
    private String title;

    public Type(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    public Type(String title) {
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
}

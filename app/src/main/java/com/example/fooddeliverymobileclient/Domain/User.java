package com.example.fooddeliverymobileclient.Domain;

public class User {
    Long id;
    String username;
    String password;
    String role;
    String number;
    byte[] img;


    public User(Long id, String username, String password, String role,String number,byte[] img) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.number=number;
        this.img=img;
    }
    public User(String username, String password,String number) {
        this.username = username;
        this.password = password;
        this.number=number;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

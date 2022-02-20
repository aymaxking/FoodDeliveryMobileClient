package com.example.fooddeliverymobileclient.Domain;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public SubMenu(JSONObject jsonObject){
        try {
            this.id=jsonObject.getLong("id");
            this.title=jsonObject.getString("title");
            this.price=jsonObject.getDouble("price");
            this.img=jsonObject.getString("img").getBytes();
        }catch (JSONException e) {
            e.printStackTrace();
        }
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

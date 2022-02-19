package com.example.fooddeliverymobileclient.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    Long id;
    String date;
    String heure;
    String etat;
    List<SubMenu> items;







    public Commande() {
        items=  new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<SubMenu> getItems() {
        return items;
    }

    public void setItems(List<SubMenu> items) {
        this.items = items;
    }
}

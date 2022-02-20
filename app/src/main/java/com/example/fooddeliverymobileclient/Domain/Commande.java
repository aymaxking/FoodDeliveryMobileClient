package com.example.fooddeliverymobileclient.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    Long id;
    String date;
    String heure;
    String etat;
    Long idclient;
    List<SubMenu> items;

    public  double getTotal(){
        double total=0;
        for(SubMenu  item : items){
            total+=item.getPrice();
        }
        return total;
    }


    public Long getIdclient() {
        return idclient;
    }

    public void setIdclient(Long idclient) {
        this.idclient = idclient;
    }

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

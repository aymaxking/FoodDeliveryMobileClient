package com.example.fooddeliverymobileclient.Domain;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
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

    public int countitems(SubMenu subMenu){
        int i=0;
        for(SubMenu s:items){
            if(s.getId()==subMenu.getId()) i++;
        }
        return i;
    }

    public  List<SubMenu> noDoubles(){
        List<SubMenu> newList=new ArrayList<SubMenu>();
        int f=0;
        for(SubMenu s:items){
            f=0;
            for(SubMenu s2:newList){
                if(s2.getId()==s.getId()){
                    f=1;
                    break;
                }
            }
            if(f==0) newList.add(s);
        }
        return newList;
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
    public Commande(JSONObject jsonObject){
        try {
        this.id=jsonObject.getLong("id");
        this.date=jsonObject.getString("date");
        this.heure=jsonObject.getString("heure");
        this.etat=jsonObject.getString("etat");
        this.items=new ArrayList<>();
            if(jsonObject.has("items")){
                JSONArray array2=jsonObject.getJSONArray("items");
                for(int j=0;j<array2.length();j++) {
                    this.items.add(new SubMenu(array2.getJSONObject(j)));
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

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

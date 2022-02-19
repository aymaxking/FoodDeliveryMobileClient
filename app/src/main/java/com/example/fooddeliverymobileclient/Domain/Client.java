package com.example.fooddeliverymobileclient.Domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Client  extends User{
    String name;
    String birthday;
    String CIN;
    String home_adresse;

    public Client(Long id, String username, String password, String role, byte[] img, String name, String birthday, String CIN, String home_adresse,String number) {
        super(id, username, password, role, number,img);
        this.name = name;
        this.birthday = birthday;
        this.CIN = CIN;
        this.home_adresse = home_adresse;
    }
    public Client(JSONObject object){
        super();
        try{
            name=object.getString("name");
            birthday=object.getString("birthday");
            CIN=object.getString("CIN");
            id=object.getLong("id");
            username=object.getString("username");
            password=object.getString("password");
            img=object.getString("img").getBytes();
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public Client(String username, String password, String name,String number) {
        super(username, password,number);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getHome_adresse() {
        return home_adresse;
    }

    public void setHome_adresse(String home_adresse) {
        this.home_adresse = home_adresse;
    }
}

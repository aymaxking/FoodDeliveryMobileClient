package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.CommandeAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.SubMenuAdapter;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterItem;
    private RecyclerView recyclerViewItemsItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        recyclerViewItems();
    }

    private void recyclerViewItems() {
        LinearLayoutManager linearLayoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewItemsItems = findViewById(R.id.orderitemslist);
        recyclerViewItemsItems.setLayoutManager(linearLayoutManagerItem);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("commande", "");
        Commande commande = gson.fromJson(json, Commande.class);
        Log.e("c",json);
        adapterItem = new CommandeAdapter(commande);
        recyclerViewItemsItems.setAdapter(adapterItem);



    }
}
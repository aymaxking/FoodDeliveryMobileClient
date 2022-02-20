package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fooddeliverymobileclient.Adapter.CommandeAdapter;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

public class OrderDetails extends AppCompatActivity {
    private RecyclerView.Adapter adapterItem;
    private RecyclerView recyclerViewItemsItems;
    TextView orderNumber;
    TextView orderTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        orderNumber=findViewById(R.id.orderNumber);
        orderTotal=findViewById(R.id.orderDetailTotal);
        recyclerViewItems();
    }

    private void recyclerViewItems() {
        LinearLayoutManager linearLayoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewItemsItems = findViewById(R.id.orderitemsdetailslist);
        recyclerViewItemsItems.setLayoutManager(linearLayoutManagerItem);
        Gson gson = new Gson();
        Commande commande = gson.fromJson(getIntent().getStringExtra("commande"), Commande.class);
        orderTotal.setText(commande.getTotal()*1.1+ " MAD");
        orderNumber.setText("Commande "+commande.getId());
        Log.e("order",commande.getItems().size()+"");
        adapterItem = new CommandeAdapter(commande);
        recyclerViewItemsItems.setAdapter(adapterItem);
    }
}
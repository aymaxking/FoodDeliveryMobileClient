package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.CommandeAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.SubMenuAdapter;
import com.example.fooddeliverymobileclient.Domain.Client;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.MainActivity;
import com.example.fooddeliverymobileclient.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardActivity extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private RecyclerView.Adapter adapterItem;
    private RecyclerView recyclerViewItemsItems;
    TextView totalItems;
    TextView total;
    TextView deliveryfees;
    TextView checkout;
    SharedPreferences sharedPreferences;
    Gson gson = new Gson();
    String json;
    SharedPreferences.Editor myEdit;
    Commande commande ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit= sharedPreferences.edit();
        json = sharedPreferences.getString("commande", "");
        commande= gson.fromJson(json, Commande.class);
        commande.setIdclient(Long.valueOf(sharedPreferences.getString("id","")));
        totalItems = findViewById(R.id.totalFeeTxt);
        deliveryfees= findViewById(R.id.deliveryTxt);
        total= findViewById(R.id.totalTxt);
        checkout=findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(CardActivity.this);
                String url = "http://10.0.2.2:8181/rest/orders";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String json = gson.toJson(new Commande());
                                myEdit.putString("commande", json);
                                myEdit.commit();
                                startActivity(new Intent(CardActivity.this, MainActivity.class));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error",error.getMessage());
                            }
                        }){
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        Gson gson = new Gson();
                        String json = gson.toJson(commande);
                        return json.getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> headers =new HashMap<String, String>();;
                        headers.put("Content-Type", "application/json;charset=utf-8");
                        return headers != null ? headers : super.getHeaders();
                    }
                };
                queue.add(stringRequest);

            }
        });


        recyclerViewItems();
        bottomNavigation();
    }

    private void recyclerViewItems() {
        LinearLayoutManager linearLayoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewItemsItems = findViewById(R.id.orderitemslist);
        recyclerViewItemsItems.setLayoutManager(linearLayoutManagerItem);
        totalItems.setText(df.format(commande.getTotal())+" MAD");
        deliveryfees.setText("10%");
        total.setText(df.format(commande.getTotal()*1.1)+ " MAD");
        adapterItem = new CommandeAdapter(commande);
        recyclerViewItemsItems.setAdapter(adapterItem);
    }

    private void bottomNavigation() {
        FloatingActionButton card = findViewById(R.id.navCardC);
        LinearLayout homeBtn = findViewById(R.id.navHomeC);
        LinearLayout ordersBtn = findViewById(R.id.navOrdersC);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this, CardActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this,MainActivity.class));
            }
        });

        ordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this,OrdersActivity.class));
            }
        });
    }
}
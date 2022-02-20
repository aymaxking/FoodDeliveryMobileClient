package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.CategoryAdapter;
import com.example.fooddeliverymobileclient.Adapter.CommandeAdapter;
import com.example.fooddeliverymobileclient.Adapter.OrdersAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.TypesAdapter;
import com.example.fooddeliverymobileclient.Domain.Category;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.Type;
import com.example.fooddeliverymobileclient.MainActivity;
import com.example.fooddeliverymobileclient.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterOrders;
    private RecyclerView recyclerViewOrderList;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        bottomNavigation();
        filldata();
    }

    public void filldata(){
        LinearLayoutManager linearLayoutManagerOrder = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderList = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrderList.setLayoutManager(linearLayoutManagerOrder);
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        ArrayList<Commande> commandes= new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8181/rest/orders/byClient/"+sharedPreferences.getString("id","");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray array=new JSONArray(response);
                        for(int i=0;i<array.length();i++) {
                            JSONObject object1=array.getJSONObject(i);
                            commandes.add(new Commande(object1));
                        }
                        adapterOrders = new OrdersAdapter(commandes,this);
                        recyclerViewOrderList.setAdapter(adapterOrders);
                        recyclerViewOrderList.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewOrderList ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {

                                    }

                                    @Override public void onLongItemClick(View view, int position) {

                                    }
                                })
                        );
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.e("err",error.getMessage());
        });
        queue.add(stringRequest);
    }

    private void bottomNavigation() {
        FloatingActionButton card = findViewById(R.id.navCardO);
        LinearLayout homeBtn = findViewById(R.id.navHomeO);
        LinearLayout ordersBtn = findViewById(R.id.navOrdersO);
        ordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdersActivity.this,OrdersActivity.class));
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdersActivity.this, CardActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrdersActivity.this, MainActivity.class));
            }
        });
    }
}
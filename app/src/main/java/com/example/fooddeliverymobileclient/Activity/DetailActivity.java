package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.SubMenuAdapter;
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

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TextView detailName;
    TextView checkout;
    TextView detailPrice;
    ImageView detailImage;
    TextView addtocard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailName= findViewById(R.id.detailName);
        checkout= findViewById(R.id.checkout);
        detailPrice= findViewById(R.id.detailPrice);
        detailImage= findViewById(R.id.detailImage);
        addtocard=findViewById(R.id.addToCardBtn);
        filldata();
        bottomNavigation();
    }

    private void filldata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8181/rest/submenus/" + getIntent().getStringExtra("subMenuId");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject object1 = new JSONObject(response);
                        SubMenu item=new SubMenu(object1.getLong("id"),object1.getString("title"),object1.getDouble("price"), Base64.decode(object1.getString("img"),Base64.DEFAULT));
                        detailName.setText(item.getTitle());
                        detailPrice.setText(item.getPrice()+" MAD");
                        Bitmap bmp= BitmapFactory.decodeByteArray(item.getImg(),0,item.getImg().length);
                        detailImage.setImageBitmap(bmp);
                        addtocard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences mPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = mPreferences.edit();
                                Gson gson = new Gson();
                                String json = mPreferences.getString("commande", "");
                                Commande commande = gson.fromJson(json, Commande.class);
                                commande.getItems().add(item);
                                myEdit.putString("commande", gson.toJson(commande));
                                myEdit.commit();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.e("err", error.getMessage());
        });
        queue.add(stringRequest);
    }

    private void bottomNavigation() {
        FloatingActionButton card = findViewById(R.id.navCardC);
        LinearLayout homeBtn = findViewById(R.id.navHomeC);
        LinearLayout ordersBtn = findViewById(R.id.navOrdersD);
        ordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,OrdersActivity.class));
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, CardActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

    }
}
package com.example.fooddeliverymobileclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Activity.IntroActivity;
import com.example.fooddeliverymobileclient.Adapter.CategoryAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.TypesAdapter;
import com.example.fooddeliverymobileclient.Domain.Category;
import com.example.fooddeliverymobileclient.Domain.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCategory, adapterType,adapterPLace;
    private RecyclerView recyclerViewCategoryList, recyclerViewTypesList,recyclerViewTypesPlaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManagerType = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManagerCategory);
        recyclerViewTypesList = findViewById(R.id.recyclerViewType);
        recyclerViewTypesList.setLayoutManager(linearLayoutManagerType);

        ArrayList<Category> categories= new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8181/rest/categories";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray array=new JSONArray(response);
                        for(int i=0;i<array.length();i++) {
                            JSONObject object1=array.getJSONObject(i);
                            String title =object1.getString("title");

                            ArrayList<Type> types= new ArrayList<>();
                            if(object1.has("types")){
                                JSONArray array2=object1.getJSONArray("types");
                                for(int j=0;j<array2.length();j++) {
                                    JSONObject object2=array2.getJSONObject(j);
                                    types.add(new Type(object2.getString("title")));
                                }
                            }
                            categories.add(new Category(title,types));
                        }
                        adapterCategory = new CategoryAdapter(categories);
                        recyclerViewCategoryList.setAdapter(adapterCategory);
                        recyclerViewCategoryList.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewCategoryList ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        adapterType = new TypesAdapter(categories.get(position).getTypes());
                                        recyclerViewTypesList.setAdapter(adapterType);
                                    }

                                    @Override public void onLongItemClick(View view, int position) {
                                        // do whatever
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

    private void recyclerViewPlaces() {
        LinearLayoutManager linearLayoutManagerPlace = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTypesPlaces = findViewById(R.id.recyclerViewCategory);
        recyclerViewTypesPlaces.setLayoutManager(linearLayoutManagerPlace);

        ArrayList<Category> categories= new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8181/rest/categories";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray array=new JSONArray(response);
                        for(int i=0;i<array.length();i++) {
                            JSONObject object1=array.getJSONObject(i);
                            String title =object1.getString("title");

                            ArrayList<Type> types= new ArrayList<>();
                            if(object1.has("types")){
                                JSONArray array2=object1.getJSONArray("types");
                                for(int j=0;j<array2.length();j++) {
                                    JSONObject object2=array2.getJSONObject(j);
                                    types.add(new Type(object2.getString("title")));
                                }
                            }
                            categories.add(new Category(title,types));
                        }
                        adapterCategory = new CategoryAdapter(categories);
                        recyclerViewCategoryList.setAdapter(adapterCategory);
                        recyclerViewCategoryList.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewCategoryList ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        adapterType = new TypesAdapter(categories.get(position).getTypes());
                                        recyclerViewTypesList.setAdapter(adapterType);
                                    }

                                    @Override public void onLongItemClick(View view, int position) {
                                        // do whatever
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
}
package com.example.fooddeliverymobileclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Activity.IntroActivity;
import com.example.fooddeliverymobileclient.Activity.MenuActivity;
import com.example.fooddeliverymobileclient.Adapter.CategoryAdapter;
import com.example.fooddeliverymobileclient.Adapter.PlaceAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.TypesAdapter;
import com.example.fooddeliverymobileclient.Domain.Category;
import com.example.fooddeliverymobileclient.Domain.Client;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.Domain.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCategory, adapterType,adapterPLace;
    private RecyclerView recyclerViewCategoryList, recyclerViewTypesList,recyclerViewTypesPlaces;
    SharedPreferences sh;
    TextView name ;
    ImageView  img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        name=findViewById(R.id.textView4);
        img=findViewById(R.id.imageView3);
        filldata();
        recyclerViewCategory();
        recyclerViewPlaces();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(new Commande());
        myEdit.putString("commande", json);
        myEdit.commit();
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

    public void filldata(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8181/rest/clients/"+sh.getString("id","");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject object=new JSONObject(response);
                         name.setText(object.getString("name"));
                         byte[] image = object.getString("img").getBytes();
                         if(image.length>6){
                             Bitmap bmp= BitmapFactory.decodeByteArray(image,0,image.length);
                             img.setImageBitmap(bmp);
                         }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.e("err",error.getMessage());
        });
        queue.add(stringRequest);
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
        LinearLayoutManager linearLayoutManagerPlace = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTypesPlaces = findViewById(R.id.recyclerViewPlace);
        recyclerViewTypesPlaces.setLayoutManager(linearLayoutManagerPlace);

        ArrayList<Place> places= new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8181/rest/places";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray array=new JSONArray(response);
                        for(int i=0;i<array.length();i++) {
                            JSONObject object1=array.getJSONObject(i);
                            String title =object1.getString("title");
                            String description = object1.getString("description");
                            String location = object1.getString("location");
                            String number = object1.getString("number");
                            String username = object1.getString("username");
                            String password = object1.getString("password");
                            String role = object1.getString("role");
                            byte[] img = Base64.decode(object1.getString("img"),Base64.DEFAULT);
                            Long id = object1.getLong("id");
                            places.add(new Place(id,username,password,role,number,img,title,description,location));
                        }
                        adapterPLace = new PlaceAdapter(places);
                        recyclerViewTypesPlaces.setAdapter(adapterPLace);
                        recyclerViewTypesPlaces.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewTypesPlaces ,new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                        intent.putExtra("placeId", places.get(position).getId());
                                        startActivity(intent);
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
package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.MenuAdapter;
import com.example.fooddeliverymobileclient.Adapter.PlaceAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.MainActivity;
import com.example.fooddeliverymobileclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterMernu;
    private RecyclerView recyclerViewMenusPlaces;
    private Long placeId;
    private Place place;
    TextView titleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        placeId = (Long) getIntent().getExtras().get("placeId");
         titleTV = findViewById(R.id.placeName);;
        recyclerViewMenus();

    }

    private void recyclerViewMenus() {
        LinearLayoutManager linearLayoutManagerPlace = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMenusPlaces = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenusPlaces.setLayoutManager(linearLayoutManagerPlace);

        ArrayList<Menu> menus = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8181/rest/places/" + placeId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject object1 = new JSONObject(response);
                        String title = object1.getString("title");
                        String description = object1.getString("description");
                        String location = object1.getString("location");
                        String number = object1.getString("number");
                        String username = object1.getString("username");
                        String password = object1.getString("password");
                        String role = object1.getString("role");
                        byte[] img = object1.getString("img").getBytes();
                        Long id = object1.getLong("id");
                        if (!object1.get("menus").toString().equals("null")) {
                            JSONArray array2 = object1.getJSONArray("menus");
                            for (int j = 0; j < array2.length(); j++) {
                                JSONObject object2 = array2.getJSONObject(j);
                                menus.add(new Menu(object2.getLong("id"), object2.getString("title")));
                                if (!object2.get("subMenus").toString().equals("null")) {
                                    JSONArray array3 = object2.getJSONArray("subMenus");
                                    for (int k = 0; k < array3.length(); k++) {
                                        JSONObject object3 = array3.getJSONObject(k);
                                        menus.get(j).getSubMenus().add(new SubMenu(object3.getLong("id"),object3.getString("title"),object3.getDouble("price"), Base64.decode(object3.getString("img"),Base64.DEFAULT)));
                                    }
                                }
                            }
                        }
                        place = new Place(id, username, password, role, number,img,title, description,location, menus);
                        titleTV.setText(place.getTitle());
                        adapterMernu = new MenuAdapter(menus);
                        recyclerViewMenusPlaces.setAdapter(adapterMernu);
                        recyclerViewMenusPlaces.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewMenusPlaces, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(MenuActivity.this, ItemActivity.class);
                                        intent.putExtra("itemId", menus.get(position).getId());
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {
                                    }
                                })
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.e("err", error.getMessage());
        });
        queue.add(stringRequest);
    }

}

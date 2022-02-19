package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fooddeliverymobileclient.Adapter.MenuAdapter;
import com.example.fooddeliverymobileclient.Adapter.RecyclerItemClickListener;
import com.example.fooddeliverymobileclient.Adapter.SubMenuAdapter;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterItem;
    private RecyclerView recyclerViewItemsItems;
    private Long itemId;
    private Menu menu;
    TextView titleTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        itemId = (Long) getIntent().getExtras().get("itemId");
        titleTV = findViewById(R.id.menuNameItem);;
        recyclerViewItems();


    }

    private void recyclerViewItems() {
        LinearLayoutManager linearLayoutManagerItem = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewItemsItems = findViewById(R.id.recyclerViewItem);
        recyclerViewItemsItems.setLayoutManager(linearLayoutManagerItem);

        ArrayList<SubMenu> subMenus = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8181/rest/menus/" + itemId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject object1 = new JSONObject(response);
                        String title = object1.getString("title");
                        Long id = object1.getLong("id");
                        if (!object1.get("subMenus").toString().equals("null")) {
                            JSONArray array2 = object1.getJSONArray("subMenus");
                            for (int j = 0; j < array2.length(); j++) {
                                JSONObject object2 = array2.getJSONObject(j);
                                subMenus.add(new SubMenu(object2.getLong("id"),object2.getString("title"),object2.getDouble("price"), Base64.decode(object2.getString("img"),Base64.DEFAULT)));
                            }
                        }
                        menu = new Menu(id, title);
                        titleTV.setText(menu.getTitle());
                        adapterItem = new SubMenuAdapter(subMenus,this);
                        recyclerViewItemsItems.setAdapter(adapterItem);
                        recyclerViewItemsItems.addOnItemTouchListener(
                                new RecyclerItemClickListener(this, recyclerViewItemsItems, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {

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
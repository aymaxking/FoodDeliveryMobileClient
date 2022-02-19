package com.example.fooddeliverymobileclient.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverymobileclient.Activity.CardActivity;
import com.example.fooddeliverymobileclient.Activity.IntroActivity;
import com.example.fooddeliverymobileclient.Activity.ItemActivity;
import com.example.fooddeliverymobileclient.Activity.LoginActivity;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.ViewHolder> {
    ArrayList<SubMenu> subMenus;
    private Context context;

    public SubMenuAdapter(ArrayList<SubMenu> subMenus,Context context) {
        this.subMenus=subMenus;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemName.setText(subMenus.get(position).getTitle());
        holder.itemPrice.setText(subMenus.get(position).getPrice()+" MAD");
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = mPreferences.edit();
                Gson gson = new Gson();
                String json = mPreferences.getString("commande", "");
                Commande commande = gson.fromJson(json, Commande.class);
                commande.getItems().add(subMenus.get(position));
                myEdit.putString("commande", gson.toJson(commande));
                myEdit.commit();
                context.startActivity(new Intent(context, CardActivity.class));
            }
        });
        Bitmap bmp= BitmapFactory.decodeByteArray(subMenus.get(position).getImg(),0,subMenus.get(position).getImg().length);
        holder.itemImage.setImageBitmap(bmp);
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
    }


    @Override
    public int getItemCount() {
        return subMenus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemPrice;
        ImageView itemImage;
        TextView addBtn;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage=itemView.findViewById(R.id.itemImage);
            addBtn=itemView.findViewById(R.id.addBtn);
            mainLayout = itemView.findViewById(R.id.mainLayoutItem);
        }
    }
}

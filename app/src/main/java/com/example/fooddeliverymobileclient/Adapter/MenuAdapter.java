package com.example.fooddeliverymobileclient.Adapter;

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

import com.example.fooddeliverymobileclient.Domain.Menu;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    ArrayList<Menu> menus;

    public MenuAdapter(ArrayList<Menu> menus) {
        this.menus=menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_menu, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.menuName.setText(menus.get(position).getTitle());
        if(menus.get(position).getSubMenus().size()>0){
            Bitmap bmp= BitmapFactory.decodeByteArray(menus.get(position).getSubMenus().get(0).getImg(),0,menus.get(position).getSubMenus().get(0).getImg().length);
            holder.menuImage1.setImageBitmap(bmp);
        }
        if(menus.get(position).getSubMenus().size()>1){
        Bitmap bmp= BitmapFactory.decodeByteArray(menus.get(position).getSubMenus().get(1).getImg(),0,menus.get(position).getSubMenus().get(1).getImg().length);
        holder.menuImage2.setImageBitmap(bmp);
        }
        if(menus.get(position).getSubMenus().size()>2){
            Bitmap bmp= BitmapFactory.decodeByteArray(menus.get(position).getSubMenus().get(2).getImg(),0,menus.get(position).getSubMenus().get(2).getImg().length);
        holder.menuImage3.setImageBitmap(bmp);
        }
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
    }


    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuName;
        ImageView menuImage1;
        ImageView menuImage2;
        ImageView menuImage3;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menuName);
            menuImage1 = itemView.findViewById(R.id.menuImage1);
            menuImage2 = itemView.findViewById(R.id.menuImage2);
            menuImage3 = itemView.findViewById(R.id.menuImage3);
            mainLayout = itemView.findViewById(R.id.mainLayoutMenu);
        }
    }
}

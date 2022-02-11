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

import com.example.fooddeliverymobileclient.Domain.Category;
import com.example.fooddeliverymobileclient.Domain.Place;
import com.example.fooddeliverymobileclient.R;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    ArrayList<Place> places;

    public PlaceAdapter(ArrayList<Place> places) {
        this.places=places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_place, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(places.get(position).getTitle());
        holder.placeDescription.setText(places.get(position).getDescription());
        Bitmap bmp= BitmapFactory.decodeByteArray(places.get(position).getImg(),0,places.get(position).getImg().length);
        holder.placeImg.setImageBitmap(bmp);
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
    }


    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placeDescription;
        ImageView placeImg;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.placeName);
            placeDescription = itemView.findViewById(R.id.placeDescription);
            placeImg = itemView.findViewById(R.id.placeImg);
            mainLayout = itemView.findViewById(R.id.mainLayoutPlace);
        }
    }
}

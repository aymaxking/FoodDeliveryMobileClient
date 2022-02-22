package com.example.fooddeliverymobileclient.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverymobileclient.Activity.DetailActivity;
import com.example.fooddeliverymobileclient.Activity.OrderDetails;
import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.R;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    ArrayList<Commande> commandes;
    private Context context;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public OrdersAdapter(ArrayList<Commande> commandes,Context context) {
        this.commandes=commandes;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OrderDetails.class);
                Gson gson = new Gson();
                String json = gson.toJson(commandes.get(position));
                i.putExtra("commande", json);
                context.startActivity(i);
            }
        });
        holder.orderDate.setText(commandes.get(position).getDate()+"  "+commandes.get(position).getHeure());
        holder.orderTotal.setText(df.format(commandes.get(position).getTotal()*1.1)+" MAD");
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
    }


    @Override
    public int getItemCount() {
        return commandes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate;
        TextView orderTotal;

        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderTotal = itemView.findViewById(R.id.orderTotal);
            mainLayout = itemView.findViewById(R.id.mainLayoutOrder);
        }
    }
}

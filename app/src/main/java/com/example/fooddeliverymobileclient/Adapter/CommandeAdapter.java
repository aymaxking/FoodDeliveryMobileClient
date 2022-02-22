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

import com.example.fooddeliverymobileclient.Domain.Commande;
import com.example.fooddeliverymobileclient.Domain.SubMenu;
import com.example.fooddeliverymobileclient.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.ViewHolder> {
    Commande commande;
    int type;


    public CommandeAdapter(Commande commande,int type) {
        this.commande=commande;
        this.type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_itemcommande, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemNameCommande.setText(commande.noDoubles().get(position).getTitle());
        holder.itemPriceCommande.setText(commande.noDoubles().get(position).getPrice()+" MAD");
        holder.itemTotalCommande.setText(commande.noDoubles().get(position).getPrice()*commande.countitems(commande.noDoubles().get(position))+" MAD");
        holder.itemcount.setText(commande.countitems(commande.noDoubles().get(position))+"");
        Bitmap bmp= BitmapFactory.decodeByteArray(commande.noDoubles().get(position).getImg(),0,commande.noDoubles().get(position).getImg().length);
        holder.itemImage.setImageBitmap(bmp);
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
        if(type==1){
            holder.itemminus.setVisibility(View.INVISIBLE);
            holder.itemplus.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return commande.noDoubles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameCommande;
        TextView itemPriceCommande;
        TextView itemTotalCommande;
        TextView itemcount;
        ImageView itemplus;
        ImageView itemminus;
        ImageView itemImage;

        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameCommande = itemView.findViewById(R.id.itemNameCommande);
            itemPriceCommande = itemView.findViewById(R.id.itemPriceCommande);
            itemTotalCommande = itemView.findViewById(R.id.itemTotalCommande);
            itemcount = itemView.findViewById(R.id.itemcount);
            itemplus = itemView.findViewById(R.id.itemplus);
            itemminus = itemView.findViewById(R.id.itemminus);
            itemImage = itemView.findViewById(R.id.itemImage);
            mainLayout = itemView.findViewById(R.id.mainLayoutItem);
        }
    }
}

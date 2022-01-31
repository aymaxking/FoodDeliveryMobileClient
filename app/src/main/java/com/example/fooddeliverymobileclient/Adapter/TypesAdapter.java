package com.example.fooddeliverymobileclient.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverymobileclient.Domain.Category;
import com.example.fooddeliverymobileclient.Domain.Type;
import com.example.fooddeliverymobileclient.R;

import java.util.ArrayList;

public class TypesAdapter extends RecyclerView.Adapter<TypesAdapter.ViewHolder> {
    ArrayList<Type> types;

    public TypesAdapter(ArrayList<Type> types) {
        this.types=types;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_type, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.typeName.setText(types.get(position).getTitle());
        holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
    }


    @Override
    public int getItemCount() {
        return types.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeName;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeName = itemView.findViewById(R.id.typeName);
            mainLayout = itemView.findViewById(R.id.mainLayoutType);
        }
    }
}

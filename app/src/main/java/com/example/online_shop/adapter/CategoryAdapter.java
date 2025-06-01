package com.example.online_shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_shop.R;
import com.example.online_shop.data.model.Collection;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Collection> collections;

    // Setter to pass data into the adapter
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
        notifyDataSetChanged(); // Tell RecyclerView to redraw
    }

    // Called when RecyclerView needs a new item view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the list
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    // Called to bind data to each item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Collection collection = collections.get(position);
        holder.categoryName.setText(collection.getTitle());
        holder.productCount.setText(collection.getProductsCount() + " items");
    }

    // Number of items to display
    @Override
    public int getItemCount() {
        return collections != null ? collections.size() : 0;
    }

    // ViewHolder class that holds references to views in each item layout
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName, productCount;

        ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);  // Make sure these match your XML
            productCount = itemView.findViewById(R.id.product_count);
        }
    }
}

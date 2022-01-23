package com.meraook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.meraook.Model.CategoryItemModel;
import com.meraook.R;

import java.util.List;


public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    List<CategoryItemModel> categoryItemModelList;

    public CategoryItemAdapter(List<CategoryItemModel> categoryItemModelList) {
        this.categoryItemModelList = categoryItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String icon=categoryItemModelList.get(position).getCategoryIcon();
        String name=categoryItemModelList.get(position).getCategoryName();
        holder.setCategoryTitle(name);
        holder.setCategoryIcon(icon);
    }


    @Override
    public int getItemCount() {
        return categoryItemModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryIcon;
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon=itemView.findViewById(R.id.category_icon);
            categoryName=itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon(String iconUrl ){
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.picture)).into(categoryIcon);
        }
        private void setCategoryTitle(String title){
            categoryName.setText(title);
        }
    }
}

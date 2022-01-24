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
import com.meraook.Model.HorizontalProductModel;
import com.meraook.R;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder> {

    List<HorizontalProductModel> horizontalProductModelList;

    public HorizontalProductAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    String image=horizontalProductModelList.get(position).getProductImage();
    String title=horizontalProductModelList.get(position).getProductTitle();
    String description=horizontalProductModelList.get(position).getProductDescription();
    String price=horizontalProductModelList.get(position).getProductPrice();

    holder.setImage(image);
    holder.setTitle(title);
    holder.setDescription(description);
    holder.setPrice(price);
    }

    @Override
    public int getItemCount() {
        return horizontalProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productTitle;
        TextView productDescription;
        TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        productImage=itemView.findViewById(R.id.h_s_product_image);
        productTitle=itemView.findViewById(R.id.h_s_product_title);
        productDescription=itemView.findViewById(R.id.h_s_product_description);
        productPrice=itemView.findViewById(R.id.h_s_product_price);
        }

        private void setImage(String image){
            Glide.with(itemView.getContext()).load(image).apply(new RequestOptions().placeholder(R.drawable.picture)).into(productImage);
        }
        private void setTitle(String title){
            productTitle.setText(title);
        }
        private void setDescription(String description){
            productDescription.setText(description);
        }
        private void setPrice(String price){
            productPrice.setText("Rs "+price);
        }
    }
}

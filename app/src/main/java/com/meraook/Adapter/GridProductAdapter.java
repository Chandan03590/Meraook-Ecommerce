package com.meraook.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.meraook.Activity.ProductDetailActivity;
import com.meraook.Model.HorizontalProductModel;
import com.meraook.R;

import java.util.List;



public class GridProductAdapter extends BaseAdapter {

    private List<HorizontalProductModel> gridProductModelList;
    private Button gridViewAllBtn;

    public GridProductAdapter(List<HorizontalProductModel> gridProductModelList) {
        this.gridProductModelList = gridProductModelList;
    }

    @Override
    public int getCount() {
        return gridProductModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_layout, null);
            view.setElevation(0);
            view.setBackgroundResource(R.color.white);


            ////////// Product Detail Activity Intent //////////

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailIntent=new Intent(parent.getContext(), ProductDetailActivity.class);
                    parent.getContext().startActivity(productDetailIntent);
                }
            });
            ////////// Rating Layout Intent //////////

            /////////////////// View All Button /////////////////

            /////////////////// View  All Button /////////////////

            ImageView product_image=view.findViewById(R.id.h_s_product_image);
            TextView product_title=view.findViewById(R.id.h_s_product_title);
            TextView  product_description=view.findViewById(R.id.h_s_product_description);
            TextView product_price=view.findViewById(R.id.h_s_product_price);

            Glide.with(parent.getContext()).load(gridProductModelList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.picture)).into(product_image);
            product_description.setText(gridProductModelList.get(position).getProductDescription());
            product_title.setText(gridProductModelList.get(position).getProductTitle());
            product_price.setText("Rs "+gridProductModelList.get(position).getProductPrice()+" /-");

        } else {
            view=convertView;
        }
        return view;
    }

}

package com.meraook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.meraook.Adapter.BannerSliderAdapter.BannerViewHolder;
import com.meraook.Model.BannerSliderModel;
import com.meraook.R;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;


public class BannerSliderAdapter extends SliderViewAdapter<BannerSliderAdapter.BannerViewHolder> {

    private Context context;
    private List<BannerSliderModel> bannerSliderModelList;

    public BannerSliderAdapter(Context context, List<BannerSliderModel> bannerSliderModelList) {
        this.context = context;
        this.bannerSliderModelList = bannerSliderModelList;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider_layout, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder viewHolder, int position) {

        Glide.with(context).load(bannerSliderModelList.get(position).getBannerImage()).into(viewHolder.bannerImage);
    }

    @Override
    public int getCount() {
        return bannerSliderModelList.size();
    }

    public class BannerViewHolder extends SliderViewAdapter.ViewHolder {

        private ImageView bannerImage;
        private CardView bannerColor;

        public BannerViewHolder(View itemView) {
            super(itemView);

            bannerImage=itemView.findViewById(R.id.banner_imageView);
            bannerColor=itemView.findViewById(R.id.banner_container);
        }
    }
}

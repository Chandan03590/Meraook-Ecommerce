package com.meraook.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.meraook.Adapter.BannerSliderAdapter;
import com.meraook.Adapter.CategoryItemAdapter;
import com.meraook.Adapter.GridProductAdapter;
import com.meraook.Adapter.HorizontalProductAdapter;
import com.meraook.Model.BannerSliderModel;
import com.meraook.Model.CategoryItemModel;
import com.meraook.Model.HorizontalProductModel;
import com.meraook.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView categoryRecyclerView,horizontalProductRecyclerView;
    private List<CategoryItemModel> categoryItemModelList;
    private CategoryItemAdapter categoryItemAdapter;
    private FirebaseFirestore firebaseFirestore;

    private List<BannerSliderModel> bannerSliderModelList;
    private BannerSliderAdapter bannerSliderAdapter;
    private SliderView bannerAutoSlider;

    private Button horizontalViewAllBtn,gridViewAllBtn;
    private List<HorizontalProductModel> horizontalProductModelList;

    private GridView gridView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView=view.findViewById(R.id.category_recyclerView);
        bannerAutoSlider=view.findViewById(R.id.bannerSliderView);
        horizontalViewAllBtn=view.findViewById(R.id.horizontal_scroll_layout_btn);
        horizontalProductRecyclerView=view.findViewById(R.id.horizontal_scroll_layout_recyclerView);
        gridViewAllBtn=view.findViewById(R.id.grid_product_layout_viewAll_btn);

        firebaseFirestore= FirebaseFirestore.getInstance();


        ///////////////////////// CATEGORY RECYCLER VIEW ///////////////////////
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        ////////// List and Adapter ///////
        categoryItemModelList=new ArrayList<>();
        categoryItemAdapter=new CategoryItemAdapter(categoryItemModelList);
        categoryRecyclerView.setAdapter(categoryItemAdapter);
        ////////// List and Adapter ///////


        ///// Firebase Firestore //////
        firebaseFirestore.collection("Category").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryItemModelList.add(new CategoryItemModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryItemAdapter.notifyDataSetChanged();
                        } else {
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        ///// Firebase Firestore //////

        ///////////////////////// CATEGORY RECYCLER VIEW ///////////////////////



        //////////////// BANNER IMAGE SLIDER ///////////////////

        bannerSliderModelList=new ArrayList<>();
        bannerSliderAdapter=new BannerSliderAdapter(getContext(),bannerSliderModelList);
        bannerAutoSlider.setSliderAdapter(bannerSliderAdapter);

        firebaseFirestore.collection("Banner Images").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                bannerSliderModelList.add(new BannerSliderModel(queryDocumentSnapshot.get("BannerImage").toString()));
                            }
                            bannerSliderAdapter.notifyDataSetChanged();

                        } else {
                            String error=task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //////////////// BANNER IMAGE SLIDER ///////////////////




        /////////////// HORIZONTAL PRODUCT SCROLL ///////////////////

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalProductRecyclerView.setLayoutManager(manager);

        horizontalProductModelList=new ArrayList<>();
         HorizontalProductAdapter horizontalProductAdapter=new HorizontalProductAdapter(horizontalProductModelList);
        horizontalProductRecyclerView.setAdapter(horizontalProductAdapter);

        firebaseFirestore.collection("Deals of the Day").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                horizontalProductModelList.add(new HorizontalProductModel(
                                        queryDocumentSnapshot.get("productImage").toString(),
                                        queryDocumentSnapshot.get("productTitle").toString(),
                                        queryDocumentSnapshot.get("productDescription").toString(),
                                        queryDocumentSnapshot.get("productPrice").toString()));
                            }
                            horizontalProductAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getContext(), "Error to load "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /////////////////// View All Button /////////////////


        /////////////////// View  All Button /////////////////

        /////////////// HORIZONTAL PRODUCT SCROLL ///////////////////








        /////////////// GRID PRODUCT LAYOUT ///////////////////

        gridView=view.findViewById(R.id.grid_product_layout_gridView);

        List<HorizontalProductModel> gridProductModelList=new ArrayList<>();
        GridProductAdapter gridProductAdapter=new GridProductAdapter(gridProductModelList);
        gridView.setAdapter(gridProductAdapter);

        firebaseFirestore.collection("Trending").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                gridProductModelList.add(new HorizontalProductModel(
                                        queryDocumentSnapshot.get("productImage").toString(),
                                        queryDocumentSnapshot.get("productTitle").toString(),
                                        queryDocumentSnapshot.get("productDescription").toString(),
                                        queryDocumentSnapshot.get("productPrice").toString()));
                            }
                            gridProductAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(getContext(), "Error to load "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        TextView gridLayoutTitle=view.findViewById(R.id.grid_product_layout_title);
//        gridLayoutTitle.setText("#Trending");


        /////////////// GRID PRODUCT LAYOUT ///////////////////

        return view;

    }
}
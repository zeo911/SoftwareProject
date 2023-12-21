package com.example.e_commerce.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.e_commerce.R;
import com.example.e_commerce.activities.ShowAllActivity;
import com.example.e_commerce.adapters.CategoryAdapter;
import com.example.e_commerce.adapters.NewProductsAdapter;
import com.example.e_commerce.adapters.PopularProductsAdapter;
import com.example.e_commerce.models.CategoryModel;
import com.example.e_commerce.models.NewProductsModel;
import com.example.e_commerce.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    TextView catShowAll,popularShowAll,newProductShowAll;
    ProgressDialog progressDialog;
    RecyclerView catRecyclerview,newProductRecyclerview,popularRecyclerview;
    //category recyclerview
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;
    //new products Recyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;
    //popular Recyclerview
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel>popularProductsModelList;


    //FireStore
    FirebaseFirestore db;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);

        db=FirebaseFirestore.getInstance(); //items in store


        progressDialog= new ProgressDialog(getActivity());
        catRecyclerview=root.findViewById(R.id.rec_category2);
        newProductRecyclerview=root.findViewById(R.id.new_product_rec2);
        popularRecyclerview=root.findViewById(R.id.popular_rec);

        catShowAll=root.findViewById(R.id.category_see_all2);
        popularShowAll=root.findViewById(R.id.popular_see_all);
        newProductShowAll=root.findViewById(R.id.newProducts_see_all2);


        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });



        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });



        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });





        ImageSlider imageSlider=root.findViewById(R.id.image_slider2);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Today Is For Online Shopping ", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount 50% OFF ", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"Just For You ", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        progressDialog.setTitle("Welcome To My Powercart App");
        progressDialog.setMessage("please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        categoryModelList=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerview.setAdapter(categoryAdapter);


        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+ task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }


                });
        //new product
        newProductRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductsModelList= new ArrayList<>();
        newProductsAdapter=new NewProductsAdapter(getContext(),newProductsModelList);
        newProductRecyclerview.setAdapter(newProductsAdapter);

        db.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+ task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }


                });
        //popular products

        popularRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductsModelList= new ArrayList<>();
        popularProductsAdapter=new PopularProductsAdapter(getContext(),popularProductsModelList);
        popularRecyclerview.setAdapter(popularProductsAdapter);

        db.collection("All Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel = document.toObject(PopularProductsModel.class);
                                popularProductsModelList.add(popularProductsModel);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+ task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }


                });

        return root;
    }
}
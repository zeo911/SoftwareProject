package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import com.example.e_commerce.R;
import com.example.e_commerce.adapters.MyCartAdapter;
import com.example.e_commerce.models.MyCartModel;
import com.example.e_commerce.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView overAllAmount;
    int overAllTotalAmount;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel>cartModelList;
    MyCartAdapter cartAdapter;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        toolbar=findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get data from cart adapter

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));


        overAllAmount=findViewById(R.id.textView4);

        recyclerView=findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartModelList=new ArrayList<>();
        cartAdapter=new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(cartAdapter);
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                        MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                        cartModelList.add(myCartModel);
                        cartAdapter.notifyDataSetChanged();
                    }
                }


            }
        });

    }

    public BroadcastReceiver mMessageReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int  totalBill =intent.getIntExtra("totalAmount",0);
            overAllAmount.setText("Total Amount:"+ totalBill+"$");

        }
    };
}
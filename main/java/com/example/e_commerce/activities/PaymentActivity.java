package com.example.e_commerce.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.e_commerce.R;

public class PaymentActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView subTotal,discount,shipping,total ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar=findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        double amount =0.0;
        amount=getIntent().getDoubleExtra("amount",0.0);

        subTotal=findViewById(R.id.sub_total);
        discount=findViewById(R.id.textView14);
        shipping=findViewById(R.id.textView15);
        total=findViewById(R.id.textView19);

        subTotal.setText(amount+"$");

    }
}
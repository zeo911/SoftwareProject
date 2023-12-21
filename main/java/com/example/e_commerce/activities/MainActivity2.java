package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.e_commerce.R;
import com.example.e_commerce.fragments.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Fragment homeFragment;
    Toolbar toolbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);


        homeFragment=new HomeFragment();
        loadFragment(homeFragment);

    }

    private void loadFragment(Fragment homeFragment) { // by7ot el fragment gwa el home container
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {// elly btzhr el menu elly feha logout w my cart elly btzhr f el toolbar
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity2.this,MainActivity.class));
            finish();

        } else if (id == R.id.menu_my_cart) {
            startActivity(new Intent(MainActivity2.this,CartActivity.class));

        }

        return true;
    }}

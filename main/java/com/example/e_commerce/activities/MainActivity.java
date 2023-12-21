package com.example.e_commerce.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    public void signUp(View view){
        String userName= name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        if (TextUtils.isEmpty(userName)){

            Toast.makeText(this,"Enter your name!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){

            Toast.makeText(this,"Enter your email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){

            Toast.makeText(this,"Enter your password!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6){
            Toast.makeText(this,"password too short,enter minimum 6 characters!",Toast.LENGTH_SHORT).show();
            return;
        }
         auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(MainActivity.this," SignUp Successfully ",Toast.LENGTH_SHORT).show();
                     Intent i =new Intent(MainActivity.this,MainActivity2.class);
                     startActivity(i);
                 }else{
                     Toast.makeText(MainActivity.this,"  SignUp failed " +task.getException() ,Toast.LENGTH_SHORT).show();
                 }

             }
         });
    }
    public void signIn(View view){
        Intent i2 =new Intent(MainActivity.this,Login.class);
        startActivity(i2);

    }
}
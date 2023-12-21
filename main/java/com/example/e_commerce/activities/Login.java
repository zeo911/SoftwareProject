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

public class Login extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        email =findViewById(R.id.email2);
        password =findViewById(R.id.password2);

    }
    public void signIn(View view) {
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        if (TextUtils.isEmpty(userEmail)){

            Toast.makeText(this,"Enter your email!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){

            Toast.makeText(this,"Enter your password!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6) {
            Toast.makeText(this, "password too short,enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "login successful",Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Login.this, MainActivity2.class);
                    startActivity(i2);
                }else{
                    Toast.makeText(Login.this,"Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void signUp(View view) {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}
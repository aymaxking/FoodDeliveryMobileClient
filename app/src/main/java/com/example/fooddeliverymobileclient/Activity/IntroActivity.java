package com.example.fooddeliverymobileclient.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fooddeliverymobileclient.MainActivity;
import com.example.fooddeliverymobileclient.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout loginbtn;
    private ConstraintLayout signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });
        signupbtn=findViewById(R.id.signupbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, SignupActivity.class));
            }
        });
    }
}
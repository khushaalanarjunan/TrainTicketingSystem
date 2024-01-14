package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class AuthenticationActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        btnLogin = findViewById(R.id.btn_login_authentication);
        btnSignUp = findViewById(R.id.btn_signup_authentication);
        ivLogo = findViewById(R.id.iv_logo_authentication);

        ivLogo.setImageResource(R.drawable.logo);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
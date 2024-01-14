package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopUpSuccessfulActivity extends AppCompatActivity {
    Button btn_topup_successful;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_successful);
        btn_topup_successful = findViewById(R.id.btn_topup_successful);
        btn_topup_successful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopUpSuccessfulActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
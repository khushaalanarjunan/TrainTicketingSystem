package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainticketsystem_hashmapbeatstherest.object.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    protected EditText etEmail, etPassword;
    protected Button btnLogin;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_signup_authentication);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                password = password.trim();
                email = email.trim();

                if(password.isEmpty() || email.isEmpty()){
                    //remind
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Please enter an email and password").setTitle("Warning").setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                goToMainActivity();
                            }else{
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
    private void goToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
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
import android.widget.ToggleButton;

import com.example.trainticketsystem_hashmapbeatstherest.object.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName, etMyKADNumber,  etEmail, etContactNumber, etPassword;
    ToggleButton toggleGender;
    Button btnSignUp;

    private FirebaseAuth mFirebaseAuth;

    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseUsers= FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        etFullName = findViewById(R.id.et_user_fullname);
        etMyKADNumber = findViewById(R.id.et_user_mykadnumber);
        toggleGender = findViewById(R.id.toggle_user_gender);
        etEmail = findViewById(R.id.et_user_email);
        etContactNumber = findViewById(R.id.et_user_contactnumber);
        etPassword = findViewById(R.id.et_user_password);
        btnSignUp = findViewById(R.id.btn_signup_register);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                password = password.trim();
                email = email.trim();

                if(password.isEmpty() || email.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Please enter an email and password").setTitle("Warning").setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String userId = mFirebaseAuth.getUid();
                                String userBalance = "0";

                                User newUser = new User(
                                        userId,
                                        etFullName.getText().toString(),
                                        etMyKADNumber.getText().toString(),
                                        toggleGender.isChecked(),
                                        etEmail.getText().toString(),
                                        etContactNumber.getText().toString(),
                                        etPassword.getText().toString(),
                                        userBalance
                                );

                                databaseUsers.child(userId).setValue(newUser);

                                goToMainActivity();
                            }else{
                                Toast.makeText(RegisterActivity.this, "The account already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private void goToMainActivity(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
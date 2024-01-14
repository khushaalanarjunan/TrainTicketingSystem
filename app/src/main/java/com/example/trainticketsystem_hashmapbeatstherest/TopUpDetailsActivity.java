package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TopUpDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    String paymentMethod;
    EditText editText;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn_pay_now;
    TextView tv4,tv7,tv9;
    CardView cardView1;
    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_details);
        databaseUsers = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        editText = findViewById(R.id.et_amount);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn_pay_now = findViewById(R.id.btn_pay_now);
        tv4 = findViewById(R.id.topupsuccess_tv_paymenttype);
        tv7 = findViewById(R.id.tv7);
        tv9 = findViewById(R.id.tv9);
        cardView1 = findViewById(R.id.cardView1);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn_pay_now.setOnClickListener(this);
        cardView1.setOnClickListener(this);

        //dynamically change textview when edittext is changed
        TextWatcher inputTextWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                tv4.setText(s.toString());
                tv7.setText(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };
        editText.addTextChangedListener(inputTextWatcher);
        Intent intent = getIntent();
        paymentMethod = intent.getStringExtra("nestedItem");
        editText.setText(intent.getStringExtra("amount"));
        //from paymentmethodsDetailActivity intent
        if(paymentMethod!=null){
            tv9.setText(paymentMethod);}
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {
            editText.setText(btn1.getText().toString());
            tv4.setText(btn1.getText().toString());
            tv7.setText(btn1.getText().toString());
        } else if (id == R.id.btn2) {
            editText.setText(btn2.getText().toString());
            tv4.setText(btn2.getText().toString());
            tv7.setText(btn2.getText().toString());
        } else if (id == R.id.btn3) {
            editText.setText(btn3.getText().toString());
            tv4.setText(btn3.getText().toString());
            tv7.setText(btn3.getText().toString());
        } else if (id == R.id.btn4) {
            editText.setText(btn4.getText().toString());
            tv4.setText(btn4.getText().toString());
            tv7.setText(btn4.getText().toString());
        } else if (id == R.id.btn5) {
            editText.setText(btn5.getText().toString());
            tv4.setText(btn5.getText().toString());
            tv7.setText(btn5.getText().toString());
        } else if (id == R.id.btn6) {
            editText.setText(btn6.getText().toString());
            tv4.setText(btn6.getText().toString());
            tv7.setText(btn6.getText().toString());
        }
        else if(id==R.id.cardView1){
            Intent intent = new Intent(TopUpDetailsActivity.this, PaymentMethodsDetailsActivity.class);
            intent.putExtra("amount",editText.getText().toString());
            startActivity(intent);
        }
        else if(id==R.id.btn_pay_now){
            if(tv7.getText().equals("")|| tv9.getText().equals("Please Select a Payment Method")){
                Toast.makeText(this,"Please Input Amount & Select Payment Method",Toast.LENGTH_SHORT).show();
            }
            if(!tv7.getText().equals("")&& !tv9.getText().equals("Please Select a Payment Method")){
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseUsers.child(currentUserUid).child("userBalance").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            // Get the current userBalance value
                            String currentBalance = task.getResult().getValue(String.class);

                            // Calculate the new balance by adding the value of tv7.getText().toString()
                            int newValue = Integer.parseInt(currentBalance) + Integer.parseInt(tv7.getText().toString());

                            // Update the userBalance with the new value
                            databaseUsers.child(currentUserUid).child("userBalance").setValue(String.valueOf(newValue))
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Successfully updated the userBalance
                                                // Get the current time
                                                /*Calendar calendar = Calendar.getInstance();
                                                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                                                int minute = calendar.get(Calendar.MINUTE);*/
                                                Date currentTime = new Date();
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                String dateString = sdf.format(currentTime);
                                                // Now you have the current hour and minute
                                                //String currentTime = hour + ":" + minute;

                                                Intent intent = new Intent(TopUpDetailsActivity.this, TopUpSuccessfulActivity.class);
                                                intent.putExtra("amount",tv7.getText().toString());
                                                intent.putExtra("paymentType",tv9.getText().toString());
                                                intent.putExtra("time",dateString);
                                                startActivity(intent);
                                            } else {
                                                // Handle the failure to update the userBalance
                                            }
                                        }
                                    });
                        } else {
                            //error fail to fetch data
                        }
                    }
                });

            }
        }
    }
}
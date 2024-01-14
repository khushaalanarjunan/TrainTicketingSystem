package com.example.trainticketsystem_hashmapbeatstherest;

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

public class TopUpDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    String paymentMethod;
    EditText editText;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn_pay_now;
    TextView tv4,tv7,tv9;
    CardView cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_details);

        editText = findViewById(R.id.et_amount);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn_pay_now = findViewById(R.id.btn_pay_now);
        tv4 = findViewById(R.id.tv4);
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
                Toast.makeText(this,"Paid. Thank You!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TopUpDetailsActivity.this, MainActivity.class);
                intent.putExtra("topup",tv4.getText().toString());
                startActivity(intent);
            }
        }
    }
}
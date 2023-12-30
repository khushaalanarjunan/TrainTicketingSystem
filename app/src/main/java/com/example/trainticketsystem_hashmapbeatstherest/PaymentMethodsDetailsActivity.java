package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.PaymentMethodsChildAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.adapter.PaymentMethodsParentAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodsDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private PaymentMethodsChildAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods_details);
        recyclerView = findViewById(R.id.main_recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();

        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add("Maybank");
        nestedList1.add("RHB");
        nestedList1.add("HSBC");
        nestedList1.add("UOB");
        nestedList1.add("Standard Chartered");

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add("Maybank2u");
        nestedList2.add("CIMB Clicks");
        nestedList2.add("RHB Now");
        nestedList2.add("Hong Leong Connect");
        nestedList2.add("HSBC Online");

        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add("Touch 'n Go eWallet");
        nestedList3.add("Boost");
        nestedList3.add("GrabPay");

        mList.add(new DataModel(nestedList1 , "Credit / Debit Card"));
        mList.add(new DataModel( nestedList2,"Online Banking (FPX)"));
        mList.add(new DataModel( nestedList3,"E-Wallet"));

        adapter = new PaymentMethodsChildAdapter(this,mList);
        recyclerView.setAdapter(adapter);

    }
}
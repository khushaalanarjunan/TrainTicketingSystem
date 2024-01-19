package com.example.trainticketsystem_hashmapbeatstherest;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trainticketsystem_hashmapbeatstherest.object.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EWalletFragment extends Fragment {
    View root;
    ImageView imageView;
    TextView textView;
    double currentBalance;
    DatabaseReference databaseUsers;
    private ListView listview;
    private ArrayAdapter<String> adapter;
    //CoordinatorLayout toDO
    List<Transaction> transactions;
    DatabaseReference databaseTransactions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_e_wallet, container, false);
        databaseUsers = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");
        imageView = root.findViewById(R.id.img_top_up);
        textView = root.findViewById(R.id.tv_balance_ewallet);
        currentBalance = Double.parseDouble(textView.getText().toString());
        transactions = new ArrayList<>();
        loadListView();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance()
                .getReference("transactions")
                .orderByChild("userId")
                .equalTo(userId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transactions.clear();
                adapter.clear();
                for (DataSnapshot transactionDataSnapshot : dataSnapshot.getChildren()) {
                    com.example.trainticketsystem_hashmapbeatstherest.object.Transaction transaction = transactionDataSnapshot.getValue(com.example.trainticketsystem_hashmapbeatstherest.object.Transaction.class);
                    transactions.add(0, transaction);
                    adapter.insert(transaction.getTransactionType() + "\n" + transaction.getTransactionAmount() + "\n" + transaction.getTransactionTime(), 0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseUsers.child(currentUserUid).child("userBalance").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    String userBalance = task.getResult().getValue(String.class);
                    currentBalance = Double.parseDouble(userBalance);
                    textView.setText(String.format("%.2f", currentBalance));
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch user balance", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TopUpDetailsActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void loadListView() {
        listview = root.findViewById(R.id.lv_transactions);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1);
        listview.setAdapter(adapter);
    }

}
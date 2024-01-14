package com.example.trainticketsystem_hashmapbeatstherest;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EWalletFragment extends Fragment {
    View root;
    ImageView imageView;
    TextView textView;
    double currentBalance;
    DatabaseReference databaseUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_e_wallet, container, false);
        databaseUsers = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");
        imageView = root.findViewById(R.id.img_top_up);
        textView = root.findViewById(R.id.tv_balance_ewallet);
        currentBalance = Double.parseDouble(textView.getText().toString());
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseUsers.child(currentUserUid).child("userBalance").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    String userBalance = task.getResult().getValue(String.class);
                    currentBalance = Double.parseDouble(userBalance);
                    textView.setText(String.format("%.2f",currentBalance));
                    // Use userBalance as needed (e.g., display it in a TextView)
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
}
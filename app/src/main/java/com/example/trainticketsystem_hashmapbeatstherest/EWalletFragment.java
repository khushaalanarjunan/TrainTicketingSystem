package com.example.trainticketsystem_hashmapbeatstherest;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class EWalletFragment extends Fragment {
    View root;
    ImageView imageView;
    TextView textView;
    double balance;
    double add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_e_wallet, container, false);
        imageView = root.findViewById(R.id.img_top_up);
        textView = root.findViewById(R.id.tv_balance_ewallet);
        balance = Double.parseDouble(textView.getText().toString());
        Intent intent = getActivity().getIntent();
        if(getActivity().getIntent().hasExtra("topup")){
        add = Double.parseDouble(intent.getStringExtra("topup"));}
        balance+=add;
        textView.setText(String.format("%.2f",balance));
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
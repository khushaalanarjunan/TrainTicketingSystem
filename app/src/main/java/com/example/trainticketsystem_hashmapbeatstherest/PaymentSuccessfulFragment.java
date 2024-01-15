package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class PaymentSuccessfulFragment extends Fragment {

    Button btnBackToHome;
    ImageView ivPaymentSuccessful;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_successful, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBackToHome = view.findViewById(R.id.btn_return);
        ivPaymentSuccessful = view.findViewById(R.id.iv_payment_successful);

        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to home page
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MyTicketsFragment()).commit();
            }
        });
    }
}
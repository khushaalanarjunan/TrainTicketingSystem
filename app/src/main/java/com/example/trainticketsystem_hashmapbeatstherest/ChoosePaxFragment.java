package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChoosePaxFragment extends Fragment {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseTickets;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_pax, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn1 = view.findViewById(R.id.btn_1);
        btn2 = view.findViewById(R.id.btn_2);
        btn3 = view.findViewById(R.id.btn_3);
        btn4 = view.findViewById(R.id.btn_4);
        btn5 = view.findViewById(R.id.btn_5);
        btn6 = view.findViewById(R.id.btn_6);
        btn7 = view.findViewById(R.id.btn_7);
        btn8 = view.findViewById(R.id.btn_8);
        btn9 = view.findViewById(R.id.btn_9);
        btn10 = view.findViewById(R.id.btn_10);




        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseTickets= FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("1");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("2");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("3");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("4");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("5");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("6");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("7");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("8");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("9");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                databaseTickets.child(currentUserUid).child("tickets").child("pax").setValue("10");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });


    }
}
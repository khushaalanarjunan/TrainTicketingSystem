package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trainticketsystem_hashmapbeatstherest.object.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BuyTicketFragment extends Fragment {

    ImageView ivLogo;
    Button btnChooseOrigin, btnChooseDestination, btnSelectPax, btnSelectDate, btnSearchSlot, btnReset;

    static List<Ticket> ticketList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_buy_ticket, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivLogo = view.findViewById(R.id.iv_logo_buyticket);
        btnChooseOrigin = view.findViewById(R.id.btn_chooseorigin_buyticket);
        btnChooseDestination = view.findViewById(R.id.btn_choosedestination_buyticket);
        btnSelectPax = view.findViewById(R.id.btn_selectpax_buyticket);
        btnSelectDate = view.findViewById(R.id.btn_selectdate_buyticket);
        btnSearchSlot = view.findViewById(R.id.btn_searchslot_buyticket);
        btnReset = view.findViewById(R.id.btn_reset_buyticket);


        ivLogo.setImageResource(R.drawable.logo);

        btnChooseDestination.setEnabled(false);
        btnSelectPax.setEnabled(false);
        btnSelectDate.setEnabled(false);
        btnSearchSlot.setEnabled(false);

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseTickets = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users").child(currentUserUid).child("tickets");

        databaseTickets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //ORIGIN
                if (dataSnapshot.child("origin").exists()) {
                    btnChooseDestination.setEnabled(true);
                    btnChooseOrigin.setEnabled(false);

                    //DESTINATION
                    if (dataSnapshot.child("destination").exists()) {
                        btnSelectPax.setEnabled(true);
                        btnChooseDestination.setEnabled(false);

                        //PAX
                        if (dataSnapshot.child("pax").exists()) {
                            btnSelectDate.setEnabled(true);
                            btnSelectPax.setEnabled(false);


                            //DATE
                            if (dataSnapshot.child("returndate").exists()) {
                                btnSearchSlot.setEnabled(true);
                                btnSelectDate.setEnabled(false);
                            } else {
                                btnSearchSlot.setEnabled(false);
                                btnSelectDate.setEnabled(true);
                            }
                        } else {
                            btnSelectDate.setEnabled(false);
                            btnSelectPax.setEnabled(true);
                        }
                    } else {
                        btnSelectPax.setEnabled(false);
                        btnChooseDestination.setEnabled(true);
                    }
                } else {
                    btnChooseDestination.setEnabled(false);
                    btnChooseOrigin.setEnabled(true);

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnChooseOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to choose origin fragment and
                ChooseOriginFragment chooseOriginFragment = new ChooseOriginFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chooseOriginFragment).commit();


            }
        });
        btnChooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDestinationFragment chooseDestinationFragment = new ChooseDestinationFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chooseDestinationFragment).commit();
            }
        });
        btnSelectPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoosePaxFragment choosePaxFragment = new ChoosePaxFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, choosePaxFragment).commit();
            }
        });
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnSearchSlot.setEnabled(true);
//                btnSelectDate.setEnabled(false);
                ChooseDateFragment chooseDateFragment = new ChooseDateFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chooseDateFragment).commit();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference databaseTickets = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users").child(currentUserUid).child("tickets");
                databaseTickets.child("origin").removeValue();
                databaseTickets.child("destination").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // 'destination' value exists, remove it
                            databaseTickets.child("destination").removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                btnChooseDestination.setEnabled(false);
                btnSelectPax.setEnabled(false);
                btnSelectDate.setEnabled(false);
                btnSearchSlot.setEnabled(false);
                btnChooseOrigin.setEnabled(true);
            }
        });

    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
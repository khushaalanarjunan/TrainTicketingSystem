package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainticketsystem_hashmapbeatstherest.object.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChooseOriginFragment extends Fragment {

    Button btnArau, btnAnakBukit, btnAlorSetar,btnGurun, btnSungaiPetani, btnTasekGelugor, btnBukitMertajam, btnNibongTebal, btnParitBuntar, btnBaganSerai, btnKualaLumpur, btn_KLSentral;
    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseTickets;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_origin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnArau = view.findViewById(R.id.btn_arau);
        btnAnakBukit = view.findViewById(R.id.btn_anakbukit);
        btnAlorSetar = view.findViewById(R.id.btn_alorsetar);
        btnGurun = view.findViewById(R.id.btn_gurun);
        btnSungaiPetani = view.findViewById(R.id.btn_sungaipetani);
        btnTasekGelugor = view.findViewById(R.id.btn_tasekgelugor);
        btnBukitMertajam = view.findViewById(R.id.btn_bukitmertajam);
        btnNibongTebal = view.findViewById(R.id.btn_nibongtebal);
        btnParitBuntar = view.findViewById(R.id.btn_paritbuntar);
        btnBaganSerai = view.findViewById(R.id.btn_baganserai);
        btnKualaLumpur = view.findViewById(R.id.btn_kualalumpur);
        btn_KLSentral = view.findViewById(R.id.btn_klsentral);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseTickets= FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");


        btnArau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Arau");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
            }
        });

        btnAnakBukit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Anak Bukit");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnAlorSetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Alor Setar");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnGurun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Gurun");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnSungaiPetani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Sungai Petani");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnTasekGelugor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Tasek Gelugor");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnBukitMertajam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Bukit Mertajam");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnNibongTebal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Nibong Tebal");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnParitBuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Parit Buntar");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnBaganSerai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Bagan Serai");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btnKualaLumpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("Kuala Lumpur");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });

        btn_KLSentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                databaseTickets.child(currentUserUid).child("tickets").child("origin").setValue("KL Sentral");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();

            }
        });
    }
}
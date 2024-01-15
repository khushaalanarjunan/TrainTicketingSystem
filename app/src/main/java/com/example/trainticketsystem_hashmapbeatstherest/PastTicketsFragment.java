package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.MyTicketRecycleViewAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.object.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PastTicketsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference databaseUsers;
    //get current user
    String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public PastTicketsFragment() {
    }

    public static PastTicketsFragment newInstance(String param1, String param2) {
        PastTicketsFragment fragment = new PastTicketsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Ticket> AllPassTicket = getAllPassTicket();

        MyTicketRecycleViewAdapter ticketRecyclerViewAdapter = new MyTicketRecycleViewAdapter(getContext(), AllPassTicket, true);
        recyclerView.setAdapter(ticketRecyclerViewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_past_tickets, container, false);
    }
    private List<Ticket> getAllPassTicket(){
        Date currentDate = new Date();
        List<Ticket>allPassTicket = new ArrayList<>();

        allPassTicket.add(new Ticket("T123102324124120", "BUKIT_MERTAJAM", "IPOH", 1, 13.134F, "A18", new Date(124, 0, 1)));
        allPassTicket.add(new Ticket("T123102324124121", "IPOH", "KAJANG", 5, 73.134F, "A18", new Date(123, 11, 20)));
        allPassTicket.add(new Ticket("T123102324124122", "BUTTERWORTH", "PENANG", 3, 43.134F, "A18", new Date(123, 11, 25)));



        return allPassTicket;
    }
}
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
        List<Ticket>allUpcomingTicket = new ArrayList<>();

        allUpcomingTicket.add(new Ticket("T123102324124123", "Origin", "Destination", 1, 13.134F, "A18", new Date()));
        allUpcomingTicket.add(new Ticket("T123102324124124", "Origin", "Destination", 1, 13.134F, "A18", new Date()));
        allUpcomingTicket.add(new Ticket("T123102324124125", "Origin", "Destination", 1, 13.134F, "A18", new Date()));


        return allUpcomingTicket;
    }
}
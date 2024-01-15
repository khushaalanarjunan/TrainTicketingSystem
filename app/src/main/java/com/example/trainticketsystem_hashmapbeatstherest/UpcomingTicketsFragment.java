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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingTicketsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingTicketsFragment extends Fragment{

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
    public UpcomingTicketsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingTicketsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingTicketsFragment newInstance(String param1, String param2) {
        UpcomingTicketsFragment fragment = new UpcomingTicketsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
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

        List<Ticket> allUpcomingTicket = getAllUpcomingTicket();

        MyTicketRecycleViewAdapter ticketRecyclerViewAdapter = new MyTicketRecycleViewAdapter(getContext(), allUpcomingTicket, false);
        recyclerView.setAdapter(ticketRecyclerViewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_tickets, container, false);
    }

    private List<Ticket> getAllUpcomingTicket(){
        List<Ticket>allUpcomingTicket = new ArrayList<>();

        allUpcomingTicket.add(new Ticket("T123102324124123", "BUKIT_MERTAJAM", "IPOH", 2, 33.134F, "A18", new Date(124, 1, 24)));
        allUpcomingTicket.add(new Ticket("T123102324124124", "IPOH", "KAJANG", 3, 53.134F, "A18", new Date(124, 2, 24)));
        allUpcomingTicket.add(new Ticket("T123102324124125", "BUTTERWORTH", "PENANG", 1, 13.134F, "A18", new Date(124, 1, 12)));


        return allUpcomingTicket;
    }
}
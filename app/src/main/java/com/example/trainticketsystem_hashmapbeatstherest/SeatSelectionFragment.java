package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.MyTicketsFragmentPagerAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.object.Seat;
import com.example.trainticketsystem_hashmapbeatstherest.object.TrainSlot;
import com.example.trainticketsystem_hashmapbeatstherest.trainCoach.CoachAFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatSelectionFragment extends Fragment {
    public static final String trainIdParam = "TRAIN_ID";

    private AppBarLayout mAppbar;
    private Toolbar mToolbarSeatSelection;
    private TabLayout mTabsCoachSelection;
    private ViewPager mViewPagerSeatSelection;

    public SeatSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        mAppbar = root.findViewById(R.id.appbar_seat_selection);
        mToolbarSeatSelection = root.findViewById(R.id.toolbar_seat_selection);
        mTabsCoachSelection = root.findViewById(R.id.tabs_coach_selection);
        mViewPagerSeatSelection = root.findViewById(R.id.view_pager_seat_selection);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarSeatSelection);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Selecting seats");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        String trainId = getArguments().getString(trainIdParam);
        DatabaseReference mDb = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("trains");
        mDb.child(trainId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(getContext(), "Failed to retrieve train data", Toast.LENGTH_SHORT).show();
                    return;
                }

                TrainSlot train = snapshot.getValue(TrainSlot.class);
                ArrayList<Seat> seats = new ArrayList<Seat>();
                Map<String, List<Seat>> seatsMap = new HashMap<>();
                for (String coach : train.getCoaches()) {
                    seatsMap.putIfAbsent(coach, new ArrayList<>());
                }

                for (DataSnapshot s : snapshot.child("seats").getChildren()) {
                    Seat seat = s.getValue(Seat.class);
                    seatsMap.get(seat.getCoach()).add(seat);
                }

                Toast.makeText(getContext(), train.getDestinationCode() + train.getOriginCode() + train.getCoaches().toString(), Toast.LENGTH_SHORT).show();
                MyTicketsFragmentPagerAdapter adapter = new MyTicketsFragmentPagerAdapter(getChildFragmentManager());
                for (String coach : train.getCoaches()) {
                    //adapter.addFrag(new CoachAFragment(seatsMap.get(coach)), coach);
                }
                mViewPagerSeatSelection.setAdapter(adapter);
                mTabsCoachSelection.setupWithViewPager(mViewPagerSeatSelection);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

}
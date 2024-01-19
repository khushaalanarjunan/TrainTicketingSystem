package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trainticketsystem_hashmapbeatstherest.enums.Station;
import com.example.trainticketsystem_hashmapbeatstherest.object.TrainSlot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SearchSlotFragment extends Fragment {

    public static final String trainIdParam = "TRAIN_ID";
    ListView listView;
    ArrayAdapter<String> adapter;
    List<TrainSlot> trainList;

    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseTrains;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search_slot, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle incomingBundle = this.getArguments();
        Station origin = (Station) incomingBundle.getSerializable("Origin");
        Station destination = (Station) incomingBundle.getSerializable("Destination");
        Long startDate = incomingBundle.getLong("startDate");
        int pax = incomingBundle.getInt("pax");

        listView = view.findViewById(R.id.lv_trainslot);
        trainList = new ArrayList<>();

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseTrains= FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("trains");

        // Read data from Firebase
        databaseTrains
                .orderByChild("originCode").equalTo(origin.name())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        trainList.clear();

                        for (DataSnapshot trainSnapshot : dataSnapshot.getChildren()) {
                            TrainSlot trainslot = trainSnapshot.getValue(TrainSlot.class);
                            Calendar cal1 = Calendar.getInstance();
                            Calendar cal2 = Calendar.getInstance();
                            cal1.setTimeInMillis(trainslot.getStartTime());
                            cal2.setTimeInMillis(startDate);
                            boolean isSameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

                            boolean isSameDestination = trainslot.getDestinationCode().equals(destination.name());
                            if (isSameDay && isSameDestination) {
                                trainList.add(trainslot);
                            }
                            //filter by origin, destination and startTime
                        }

                        updateListView();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

        //click on the item in the listview
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            TrainSlot trainSlot = trainList.get(position);
            String trainSlotId = trainSlot.getId();

            Bundle bundle = new Bundle();
            bundle.putString(this.trainIdParam, trainSlotId);
            bundle.putString("pax", String.valueOf(pax));

            ConfirmBookingDetailFragment confirmBookingDetailFragment = new ConfirmBookingDetailFragment();
            confirmBookingDetailFragment.setArguments(bundle);
            getParentFragmentManager().beginTransaction().replace(R.id.container, confirmBookingDetailFragment).addToBackStack(null).commit();
        });
    }

    private void updateListView() {
        List<String> trainInfoList = new ArrayList<>();
        if (trainList.isEmpty()) {
            trainInfoList.add("No train available");
            //disable the button
            listView.setEnabled(false);
        }
        for (TrainSlot trainslot : trainList) {
            String trainInfo = trainslot.getId() + "\n" + trainslot.getCode() + "\n" + trainslot.getOriginCode() + "\n" + trainslot.getDestinationCode() + "\n" + trainslot.getStartTime() + "\n" + trainslot.getDuration() + "\n" + trainslot.getType();
            trainInfoList.add(trainInfo);
        }

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, trainInfoList);
        listView.setAdapter(adapter);


    }

}
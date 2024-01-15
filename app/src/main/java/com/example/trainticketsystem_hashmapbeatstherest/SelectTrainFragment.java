package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.SelectTrainRecycleViewAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.object.TrainSlot;

import java.util.List;


public class SelectTrainFragment extends Fragment {

    Toolbar toolbar;
    TextView tvDate, tvOrigin, tvDestination;

    

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_select_train, container, false);
        //init toolbar
        toolbar = root.findViewById(R.id.SelectTrain_toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Select Departing Train");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BuyTicketFragment()).commit();
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.buy_ticket);
            }
        });


        tvDate = root.findViewById(R.id.SelectTrain_tv_date);
        tvOrigin = root.findViewById(R.id.SelectTrain_tv_origin);
        tvDestination = root.findViewById(R.id.SelectTrain_tv_destination);



        tvDate.setText("");                 //to complete
        tvOrigin.setText("");               //to complete
        tvDestination.setText("");          //to complete
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.SelectTrain_recycle_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<TrainSlot> allTrainSlot = getAllTrainSlot();

        SelectTrainRecycleViewAdapter selectTrainRecycleViewAdapter = new SelectTrainRecycleViewAdapter(getContext(), allTrainSlot);
        recyclerView.setAdapter(selectTrainRecycleViewAdapter);
    }
    public SelectTrainFragment() {
    }

    public List<TrainSlot> getAllTrainSlot(){

        return null;                    // to complete
    }
}
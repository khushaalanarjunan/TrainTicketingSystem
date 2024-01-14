package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ChooseDateFragment extends Fragment {
    DatePicker datePicker;

    private FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseTickets;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_date, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        datePicker = view.findViewById(R.id.datePicker);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseTickets= FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    Toast.makeText(getContext(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();

                    String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseTickets.child(currentUserUid).child("tickets").child("departuredate").setValue(selectedDate);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChooseReturnDateFragment()).commit();
                }
            });
        }
    }
}
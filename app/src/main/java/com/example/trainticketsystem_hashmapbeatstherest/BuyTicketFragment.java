package com.example.trainticketsystem_hashmapbeatstherest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.trainticketsystem_hashmapbeatstherest.enums.Station;
import com.example.trainticketsystem_hashmapbeatstherest.object.Ticket;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;


public class BuyTicketFragment extends Fragment {

    ImageView ivLogo;
    Button btnChooseOrigin, btnChooseDestination, btnSelectPax, btnSelectDate, btnSearchSlot, btnReset;
    Station origin;
    Station destination;
    int pax;
    long startDate = 0;
    long endDate = 0;

    static List<Ticket> ticketList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseTickets = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users").child(currentUserUid).child("tickets");
        btnChooseOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] items = Arrays.stream(Station.values()).map(Object::toString).toArray(String[]::new);
                new AlertDialog.Builder(getContext()).setItems(items, (dialog, which) -> {
                    origin = Station.values()[which];
                    btnChooseOrigin.setText(origin.name);
                }).show();
            }
        });
        btnChooseDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] items = Arrays.stream(Station.values()).map(Object::toString).toArray(String[]::new);
                new AlertDialog.Builder(getContext()).setItems(items, (dialog, which) -> {
                    destination = Station.values()[which];
                    btnChooseDestination.setText(destination.name);
                }).show();
            }
        });
        btnSelectPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] items = IntStream.range(1, 10).boxed().map(Object::toString).toArray(String[]::new);
                new AlertDialog.Builder(getContext()).setItems(items, (dialog, which) -> {
                    pax = which + 1;
                    btnSelectPax.setText(String.format("%d pax", pax));
                }).show();
            }
        });
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> datepicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Train Start Date")
                        .setCalendarConstraints(
                                new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
                        )
                        .setSelection(startDate != 0 ? startDate : MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

                datepicker.addOnPositiveButtonClickListener(selection -> {
                    startDate = selection;
                });

                datepicker.show(getParentFragmentManager(), null);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChooseDestination.setText("Choose Origin");
                origin = null;
                btnChooseDestination.setText("Choose Destination");
                destination = null;
                btnSelectPax.setText("Select Pax");
                pax = 0;
                startDate = 0;
            }
        });

        btnSearchSlot.setOnClickListener(v -> {
            if(origin == null) {
                Toast.makeText(getContext(), "Please select an origin", Toast.LENGTH_SHORT).show();
                return;
            }

            if(destination == null) {
                Toast.makeText(getContext(), "Please select a destination", Toast.LENGTH_SHORT).show();
                return;
            }

            if(startDate == 0) {
                Toast.makeText(getContext(), "Please select a start date", Toast.LENGTH_SHORT).show();
                return;
            }

            if(pax == 0) {
                Toast.makeText(getContext(), "Please select number of passengers", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putSerializable("Origin", origin);
            bundle.putSerializable("Destination", destination);
            bundle.putLong("startDate", startDate);
            bundle.putInt("pax", pax);

            getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, SearchSlotFragment.class, bundle, null)
                .commit();
        });

    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
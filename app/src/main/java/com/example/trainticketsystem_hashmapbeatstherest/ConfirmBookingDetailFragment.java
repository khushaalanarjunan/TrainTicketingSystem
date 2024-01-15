package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;

public class ConfirmBookingDetailFragment extends Fragment {

    TextView tvOrigin, tvDestination, tvTrainID, tvDepartureDate, tvDepartureTime, tvPrice,tvCoach, tvSeat, tvTotalPrice;

    Button btnMakePayment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_booking_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //grab the bundle from the previous fragment
        Bundle bundle = getArguments();
        //grab the data from the bundle
        String trainSlotId = bundle.getString("trainSlotId");
        String trainSlotCode = bundle.getString("trainSlotCode");
        String trainSlotOriginCode = bundle.getString("trainSlotOriginCode");
        String trainSlotDestinationCode = bundle.getString("trainSlotDestinationCode");
        Long trainSlotStartTime =  bundle.getLong("trainSlotStartTime");
        Long trainSlotDuration = bundle.getLong("trainSlotDuration");
        String trainSlotType = bundle.getString("trainSlotType");


        tvOrigin = view.findViewById(R.id.tv_origin);
        tvDestination = view.findViewById(R.id.tv_destination);
        tvTrainID = view.findViewById(R.id.tv_train_id);
        tvDepartureDate = view.findViewById(R.id.tv_departure_date);
        tvDepartureTime = view.findViewById(R.id.tv_departure_time);
        tvPrice = view.findViewById(R.id.tv_price);
        tvCoach = view.findViewById(R.id.tv_coach);
        tvSeat = view.findViewById(R.id.tv_seat);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        btnMakePayment = view.findViewById(R.id.btn_make_payment);

        tvOrigin.setText(trainSlotOriginCode);
        tvDestination.setText(trainSlotDestinationCode);
        tvTrainID.setText(trainSlotCode);
        Date date=new Date(trainSlotStartTime);
        tvDepartureDate.setText(date.toString());
        tvDepartureTime.setText(date.toString());
        tvPrice.setText("RM 100");
        tvCoach.setText(trainSlotType);
        tvSeat.setText("A1");
        tvTotalPrice.setText("RM 100");


        //go to  add payment method fragment
        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trainSlotId", trainSlotId);
                bundle.putString("trainSlotCode", trainSlotCode);
                bundle.putString("trainSlotOriginCode", trainSlotOriginCode);
                bundle.putString("trainSlotDestinationCode", trainSlotDestinationCode);
                bundle.putLong("trainSlotStartTime", trainSlotStartTime);
                bundle.putLong("trainSlotDuration", trainSlotDuration);
                bundle.putString("trainSlotType", trainSlotType);

                Fragment fragment = new PaymentSuccessfulFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

            }
        });

    }
}
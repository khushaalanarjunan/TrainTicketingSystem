package com.example.trainticketsystem_hashmapbeatstherest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketsystem_hashmapbeatstherest.R;
import com.example.trainticketsystem_hashmapbeatstherest.TicketDetailActivity;
import com.example.trainticketsystem_hashmapbeatstherest.object.Ticket;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyTicketRecycleViewAdapter extends RecyclerView.Adapter<MyTicketRecycleViewAdapter.TicketViewHolder>{
    public List<Ticket> ticketList;
    private Context context;
    public DecimalFormat df = new DecimalFormat("0.00");
    public DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");


    public MyTicketRecycleViewAdapter(Context context,List<Ticket> ticketList){
        this.ticketList = ticketList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTicketRecycleViewAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ticket_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_row, null);

        TicketViewHolder ticketVH = new TicketViewHolder(ticket_row);

        return ticketVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketRecycleViewAdapter.TicketViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //set text here
        holder.tvTicketID.setText(ticketList.get(position).getTicketID());
        holder.tvTicketPrice.setText("RM " + df.format(ticketList.get(position).getTicketPrice()));
        holder.tvPax.setText(ticketList.get(position).getTicketPax() + " PAX");
        holder.tvDate.setText(dateFormat.format(ticketList.get(position).getTicketDate()));
        holder.tvOrigin.setText(ticketList.get(position).getTicketOrigin());
        holder.tvDestination.setText(ticketList.get(position).getTicketDestination());
        holder.btnRefund.setTag(position);
        // when click refund button
        holder.btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create alert message
                AlertDialog.Builder alert  = new AlertDialog.Builder(context);

                alert.setTitle("Refund Ticket");
                alert.setMessage("Are you sure you want to refund it?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete ticket from firebase and refund money into wallet.
                        DatabaseReference dr = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("tickets").child(ticketList.get(position).getTicketID());
                        dr.removeValue();
                        Toast.makeText(context, "Ticket Refunded", Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert.show();

            }
        });
        holder.btnViewQR.setTag(position);
        holder.btnViewQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TicketDetailActivity.class);
                intent.putExtra("ticketId", ticketList.get(position).getTicketID());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvTicketID, tvTicketPrice, tvPax, tvDate, tvOrigin, tvDestination;
        public Button btnRefund, btnViewQR;


        public TicketViewHolder(View itemView){
            super(itemView);
            //declare tv and btn
            tvTicketID = itemView.findViewById(R.id.MyTicket_tv_ticketid);
            tvTicketPrice = itemView.findViewById(R.id.MyTicket_tv_ticketprice);
            tvPax = itemView.findViewById(R.id.MyTicket_tv_pax);
            tvDate = itemView.findViewById(R.id.MyTicket_tv_date);
            tvOrigin = itemView.findViewById(R.id.MyTicket_tv_origin);
            tvDestination = itemView.findViewById(R.id.MyTicket_tv_destination);
            btnRefund = itemView.findViewById(R.id.MyTicket_btn_refund);
            btnViewQR = itemView.findViewById(R.id.MyTicket_btn_viewqr);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Ticket ID: " +  ticketList.get(getBindingAdapterPosition()).getTicketID(), Toast.LENGTH_SHORT).show();
            //show qr
        }
    }
}

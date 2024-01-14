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
import com.example.trainticketsystem_hashmapbeatstherest.object.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyTicketRecycleViewAdapter extends RecyclerView.Adapter<MyTicketRecycleViewAdapter.TicketViewHolder>{
    public List<Ticket> ticketList;
    private Context context;
    private Boolean isPassTicket;
    public DecimalFormat df = new DecimalFormat("0.00");
    public DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    DatabaseReference databaseUsers;

    public MyTicketRecycleViewAdapter(Context context,List<Ticket> ticketList,Boolean isPassTicket){
        this.ticketList = ticketList;
        this.context = context;
        this.isPassTicket = isPassTicket;
    }

    @NonNull
    @Override
    public MyTicketRecycleViewAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ticket_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_row, parent, false);

        TicketViewHolder ticketVH = new TicketViewHolder(ticket_row);

        return ticketVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketRecycleViewAdapter.TicketViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //if is pass ticket, qrcode and refund button cannot be pressed.
        if(isPassTicket){
            holder.btnViewQR.setEnabled(false);
            holder.btnRefund.setEnabled(false);
        }

        //set text here
        Ticket ticket = ticketList.get(position);
        holder.tvTicketID.setText(ticket.getTicketID());
        holder.tvTicketPrice.setText("RM " + df.format(ticket.getTicketPrice()));
        holder.tvPax.setText(ticket.getTicketPax() + " PAX");
        holder.tvDate.setText(dateFormat.format(ticket.getTicketDate()));
        holder.tvOrigin.setText(ticket.getTicketOrigin());
        holder.tvDestination.setText(ticket.getTicketDestination());
        holder.btnRefund.setTag(position);

        databaseUsers = FirebaseDatabase.getInstance("https://hashmapbeatstherest-default-rtdb.firebaseio.com/").getReference("users");

        //get current user
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
                        //add refunded money in the balance
                        databaseUsers.child(currentUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                double balance;
                                if (snapshot.exists()) {
                                    User user = snapshot.getValue(User.class);
                                    balance = Float.parseFloat(user.getUserBalance());
                                    balance += (ticket.getTicketPrice() * 0.8);
                                    databaseUsers.child(currentUserUid).child("userBalance").setValue(String.valueOf(balance));
                                    Toast.makeText(context, "Balance Refunded: RM" + df.format(ticket.getTicketPrice() * 0.8), Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(context, "Balance does not exist", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //does nothing
                            }
                        });
                        //delete ticket from firebase and refund money into wallet.

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
                intent.putExtra("ticketId", ticket.getTicketID());
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

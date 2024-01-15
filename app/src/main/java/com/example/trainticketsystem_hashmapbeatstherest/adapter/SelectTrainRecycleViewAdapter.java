package com.example.trainticketsystem_hashmapbeatstherest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainticketsystem_hashmapbeatstherest.R;
import com.example.trainticketsystem_hashmapbeatstherest.object.TrainSlot;

import java.util.List;

public class SelectTrainRecycleViewAdapter extends RecyclerView.Adapter<SelectTrainRecycleViewAdapter.TrainSlotViewHolder>{
    private Context context;
    public List<TrainSlot> trainSlotList;

    public SelectTrainRecycleViewAdapter(Context context, List<TrainSlot> trainSlotList){
        this.trainSlotList = trainSlotList;
        this.context = context;
    }


    @NonNull
    @Override
    public SelectTrainRecycleViewAdapter.TrainSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View train_slot_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_slot_row, parent, false);

        TrainSlotViewHolder trainSlotVH = new TrainSlotViewHolder(train_slot_row);

        return trainSlotVH;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTrainRecycleViewAdapter.TrainSlotViewHolder holder, int position) {
        TrainSlot trainSlot = trainSlotList.get(position);
        //set text
        holder.tvStartEndTime.setText("");            // to complete
        holder.tvPrice.setText("");                    //to complete
        holder.tvRemark.setText("");                    //to complete
        holder.tvType.setText(trainSlot.getType());
        holder.tvTrainCode.setText(trainSlot.getCode());
        holder.tvDuration.setText("");                      // to complete
    }



    @Override
    public int getItemCount() {
        return trainSlotList.size();
    }

    public class TrainSlotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvStartEndTime, tvDuration, tvType, tvTrainCode, tvRemark, tvPrice;
        public TrainSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartEndTime = itemView.findViewById(R.id.SelectTrain_tv_startendtime);
            tvDuration = itemView.findViewById(R.id.SelectTrain_tv_duration);
            tvType = itemView.findViewById(R.id.SelectTrain_tv_type);
            tvTrainCode = itemView.findViewById(R.id.SelectTrain_tv_trainCode);
            tvRemark = itemView.findViewById(R.id.SelectTrain_tv_remark);
            tvPrice = itemView.findViewById(R.id.SelectTrain_tv_price);

        }

        @Override
        public void onClick(View v) {
            //do something
        }
    }
}

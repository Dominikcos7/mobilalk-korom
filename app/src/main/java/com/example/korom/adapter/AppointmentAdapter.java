package com.example.korom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korom.R;
import com.example.korom.model.Appointment;
import com.example.korom.model.Comment;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private ArrayList<Appointment> mItemsData;
    private ArrayList<Appointment> mItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    public AppointmentAdapter(Context context, ArrayList<Appointment> itemsData) {
        mItemsData = itemsData;
        mItemsDataAll = itemsData;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment currentItem = mItemsData.get(position);
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mItemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView time;
        private TextView desc;
        private Button cancelButton;
        private Button editButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.appointmentAppointmentTime);
            date = itemView.findViewById(R.id.appointmentAppointmentDate);
            desc = itemView.findViewById(R.id.appointmentAppointmentDesc);
            cancelButton = itemView.findViewById(R.id.appointmentCancelButton);
            editButton = itemView.findViewById(R.id.appointmentEditButton);
        }

        public void bindTo(Appointment currentItem){
            String timeStr = "Time: " + currentItem.getTime();
            String dateStr = "Date: " + currentItem.getDate();
            time.setText(timeStr);
            date.setText(dateStr);
            desc.setText(currentItem.getDescription());
            cancelButton.setTag(currentItem.getId());
            editButton.setTag(currentItem.getId());
        }
    }
}

package com.kingtonytech.horems.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Staff;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    private final List<Staff> staffList;

    public StaffAdapter(List<Staff> staffList) {
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_staff, parent, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = staffList.get(position);
        holder.name.setText(staff.getStaff_name());
        holder.role.setText(staff.getStaff_role());
        holder.shift.setText(staff.getShift_time());
        holder.date.setText(staff.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    static class StaffViewHolder extends RecyclerView.ViewHolder {
        TextView name, role, shift, date;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textStaffName);
            role = itemView.findViewById(R.id.textStaffRole);
            shift = itemView.findViewById(R.id.textStaffShift);
            date = itemView.findViewById(R.id.textStaffDate);
        }
    }
}
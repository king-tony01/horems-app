package com.kingtonytech.horems.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Patient;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private final List<Patient> patientList;

    public PatientAdapter(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.name.setText(patient.getPatient_name());
        holder.age.setText(String.valueOf(patient.getAge()));
        holder.condition.setText(patient.getPatient_condition());
        holder.date.setText(patient.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, condition, date;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textPatientName);
            age = itemView.findViewById(R.id.textPatientAge);
            condition = itemView.findViewById(R.id.textPatientCondition);
            date = itemView.findViewById(R.id.textPatientDate);
        }
    }
}

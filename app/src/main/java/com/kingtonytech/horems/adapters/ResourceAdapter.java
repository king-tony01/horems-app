package com.kingtonytech.horems.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingtonytech.horems.R;
import com.kingtonytech.horems.models.Resource;

import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private final List<Resource> resourceList;

    public ResourceAdapter(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resource, parent, false);
        return new ResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        Resource resource = resourceList.get(position);
        holder.type.setText(resource.getResource_type());
        holder.available.setText((resource.isAvailable() ? "Yes" : "No"));
        holder.quantity.setText(String.valueOf(resource.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return resourceList.size();
    }

    static class ResourceViewHolder extends RecyclerView.ViewHolder {
        TextView type, available, quantity;

        public ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.textResourceType);
            available = itemView.findViewById(R.id.textResourceAvailable);
            quantity = itemView.findViewById(R.id.textResourceQuantity);
        }
    }
}
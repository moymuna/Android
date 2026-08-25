package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.R;
import com.example.myaplication.model.response.AdvanceResponse;
import java.util.List;
import java.util.Locale;

public class AdvanceAdapter extends RecyclerView.Adapter<AdvanceAdapter.ViewHolder> {

    private final List<AdvanceResponse> list;

    public AdvanceAdapter(List<AdvanceResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdvanceResponse advance = list.get(position);

        holder.tvAmount.setText(String.format(Locale.getDefault(), "₹ %.2f", advance.getAmount()));
        holder.tvStatus.setText(advance.getStatus() != null ? advance.getStatus() : "PENDING");
        holder.tvReason.setText(advance.getReason());
        holder.tvRequiredDate.setText(advance.getRequiredByDate());
        holder.tvInstallments.setText(String.valueOf(advance.getInstallments()));

        // Status coloring
        if ("APPROVED".equalsIgnoreCase(advance.getStatus())) {
            holder.tvStatus.setTextColor(0xFF16A34A);
        } else if ("REJECTED".equalsIgnoreCase(advance.getStatus())) {
            holder.tvStatus.setTextColor(0xFFDC2626);
        } else {
            holder.tvStatus.setTextColor(0xFFF59E0B);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount, tvStatus, tvReason, tvRequiredDate, tvInstallments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvRequiredDate = itemView.findViewById(R.id.tvRequiredDate);
            tvInstallments = itemView.findViewById(R.id.tvInstallments);
        }
    }
}

package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.R;
import com.example.myaplication.model.response.LeaveBalanceResponse;

import java.util.List;

public class LeaveBalanceAdapter extends RecyclerView.Adapter<LeaveBalanceAdapter.ViewHolder> {

    private List<LeaveBalanceResponse> balanceList;

    public LeaveBalanceAdapter(List<LeaveBalanceResponse> balanceList) {
        this.balanceList = balanceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leave_balance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaveBalanceResponse balance = balanceList.get(position);
        if (balance.getLeaveTypeName() != null) {
            holder.tvLeaveType.setText(balance.getLeaveTypeName().name().replace("_", " "));
        }
        holder.tvEntitled.setText(String.valueOf(balance.getTotalEntitled()));
        holder.tvUsed.setText(String.valueOf(balance.getUsed()));
        holder.tvRemaining.setText(String.valueOf(balance.getRemaining()));
    }

    @Override
    public int getItemCount() {
        return balanceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeaveType, tvEntitled, tvUsed, tvRemaining;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvEntitled = itemView.findViewById(R.id.tvEntitled);
            tvUsed = itemView.findViewById(R.id.tvUsed);
            tvRemaining = itemView.findViewById(R.id.tvRemaining);
        }
    }
}

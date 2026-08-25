package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.R;
import com.example.myaplication.model.response.HolidayResponse;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private final List<HolidayResponse> holidays;

    public HolidayAdapter(List<HolidayResponse> holidays) {
        this.holidays = holidays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holiday, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HolidayResponse holiday = holidays.get(position);
        holder.tvName.setText(holiday.getName());
        holder.tvDate.setText(holiday.getDate());
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_holiday_name);
            tvDate = itemView.findViewById(R.id.tv_holiday_date);
        }
    }
}

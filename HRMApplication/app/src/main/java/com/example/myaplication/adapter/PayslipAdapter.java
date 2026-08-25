package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.R;
import com.example.myaplication.model.response.PayslipResponse;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.ViewHolder> {

    private final List<PayslipResponse> payslips;

    public PayslipAdapter(List<PayslipResponse> payslips) {
        this.payslips = payslips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payslip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PayslipResponse payslip = payslips.get(position);
        
        String monthName = new DateFormatSymbols().getMonths()[payslip.getMonth() - 1];
        holder.tvMonthYear.setText(String.format("%s %d", monthName, payslip.getYear()));
        holder.tvStatus.setText(String.format("Status: %s", payslip.getStatus()));
        holder.tvNetSalary.setText(formatCurrency(payslip.getNetSalary()));
    }

    @Override
    public int getItemCount() {
        return payslips.size();
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) amount = BigDecimal.ZERO;
        return NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(amount);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonthYear, tvStatus, tvNetSalary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonthYear = itemView.findViewById(R.id.tv_month_year);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvNetSalary = itemView.findViewById(R.id.tv_net_salary);
        }
    }
}

package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.R;
import com.example.myaplication.model.response.TrainingResponse;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {
    private List<TrainingResponse> trainingList;
    private OnApplyClickListener listener;

    public interface OnApplyClickListener {
        void onApplyClick(TrainingResponse training);
    }

    public TrainingAdapter(List<TrainingResponse> trainingList, OnApplyClickListener listener) {
        this.trainingList = trainingList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training, parent, false);
        return new TrainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        TrainingResponse training = trainingList.get(position);
        holder.tvTrainingTitle.setText(training.getTrainingTitle());
        holder.tvTrainingStartDate.setText(training.getStartDate());
        holder.tvTrainingEndDate.setText(training.getEndDate());

        holder.btnApplyTraining.setOnClickListener(v -> {
            if (listener != null) {
                listener.onApplyClick(training);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingList != null ? trainingList.size() : 0;
    }

    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        TextView tvTrainingTitle, tvTrainingStartDate, tvTrainingEndDate;
        MaterialButton btnApplyTraining;

        public TrainingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrainingTitle = itemView.findViewById(R.id.tvTrainingTitle);
            tvTrainingStartDate = itemView.findViewById(R.id.tvTrainingStartDate);
            tvTrainingEndDate = itemView.findViewById(R.id.tvTrainingEndDate);
            btnApplyTraining = itemView.findViewById(R.id.btnApplyTraining);
        }
    }
}

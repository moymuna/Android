package com.example.myaplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplication.R;
import com.example.myaplication.model.response.ProjectResponse;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<ProjectResponse> projectList;

    public ProjectAdapter(List<ProjectResponse> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        ProjectResponse project = projectList.get(position);
        holder.tvProjectName.setText(project.getProjectName());
        holder.tvProjectDescription.setText(project.getDescription());
        holder.tvStartDate.setText(project.getStartDate());
        holder.tvEndDate.setText(project.getEndDate());
    }

    @Override
    public int getItemCount() {
        return projectList != null ? projectList.size() : 0;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvProjectName, tvProjectDescription, tvStartDate, tvEndDate;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProjectName = itemView.findViewById(R.id.tvProjectName);
            tvProjectDescription = itemView.findViewById(R.id.tvProjectDescription);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);
        }
    }
}

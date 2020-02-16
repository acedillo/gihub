package com.example.github.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.github.R;
import com.example.github.model.GithubResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitViewHolder> {

    private List<GithubResponse> list;

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_commit, parent, false);
        return new CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        GithubResponse data = list.get(position);
        holder.name.setText(data.getCommit().getAuthor().getName());
        holder.message.setText(data.getCommit().getMessage());
        holder.number.setText(data.getSha());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommitViewHolder extends RecyclerView.ViewHolder{

        TextView name = itemView.findViewById(R.id.commit_view_name);
        TextView message = itemView.findViewById(R.id.commit_view_message);
        TextView number = itemView.findViewById(R.id.commit_view_commit_number);

        public CommitViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}

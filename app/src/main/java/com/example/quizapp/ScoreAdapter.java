package com.example.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnScoreItemClickListener {
        void onScoreItemClickListener(Score score);
    }

    public class ViewHolderScore extends RecyclerView.ViewHolder {
        TextView tvId ;
        TextView tvExam;
        TextView tvScore;

        public ViewHolderScore(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvExam = itemView.findViewById(R.id.tvExam);
            tvScore = itemView.findViewById(R.id.tvScore);

        }
    }

    public ScoreAdapter(List<Score> scores, OnScoreItemClickListener scoreItemClickListener) {
        this.scores = scores;
        this.scoreItemClickListener = scoreItemClickListener;
    }

    private List<Score> scores;
    private OnScoreItemClickListener scoreItemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_score, parent, false);
        return new ViewHolderScore(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Score score = scores.get(position);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ViewHolderScore viewHolderScore = (ViewHolderScore) holder;

        viewHolderScore.tvId.setText(score.getId());
        viewHolderScore.tvExam.setText(score.getName());
        viewHolderScore.tvScore.setText(score.score);
        viewHolderScore.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreItemClickListener.onScoreItemClickListener(score);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }
}

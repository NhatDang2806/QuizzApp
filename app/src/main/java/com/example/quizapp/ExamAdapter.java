package com.example.quizapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnExamItemClickListener {
        void onExamItemClick(Exam exam);
    }
    public class ViewHolderExam extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivImage;

        public ViewHolderExam(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
    private List<Exam> exams;
    private OnExamItemClickListener examItemClickListener;

    public ExamAdapter(List<Exam> exams, OnExamItemClickListener examItemClickListener) {
        this.exams = exams;
        this.examItemClickListener = examItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_exam, parent, false);
        return new ViewHolderExam(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Exam exam = exams.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ViewHolderExam viewHolderExam = (ViewHolderExam) holder;
        StorageReference profileRef = storageReference.child("exams/"+ exam.getImage());
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHolderExam.ivImage);
            }
        });
        viewHolderExam.tvName.setText(exam.getName());

        viewHolderExam.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examItemClickListener.onExamItemClick(exam);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }
}


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

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnQuestionItemClickListener {
        void onQuestionItemClick(Question question);
    }



    public class ViewHolderQuestion extends RecyclerView.ViewHolder {
        TextView tvQuestion ;
        TextView tvAns;

        public ViewHolderQuestion(View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvAns = itemView.findViewById(R.id.tvAns);
        }
    }
    private List<Question> questions;
    private OnQuestionItemClickListener questionItemClickListener;

    public QuestionAdapter(List<Question> questions, OnQuestionItemClickListener questionItemClickListener) {
        this.questions = questions;
        this.questionItemClickListener = questionItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolderQuestion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Question question = questions.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ViewHolderQuestion viewHolderQuestion = (ViewHolderQuestion) holder;
//        StorageReference profileRef = storageReference.child("exams/"+ exam.getImage());
//        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).into(viewHolderExam.ivImage);
//            }
//        });
        viewHolderQuestion.tvQuestion.setText(question.getQuestion());
        viewHolderQuestion.tvAns.setText(question.getAns());
        viewHolderQuestion.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionItemClickListener.onQuestionItemClick(question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}


package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionDetailActivity extends AppCompatActivity {

    Question question;
    TextView tvquestion, optiona, optionb, optionc, optiond, ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        tvquestion = findViewById(R.id.question);
        optiona = findViewById(R.id.optiona);
        optionb = findViewById(R.id.optionb);
        optionc = findViewById(R.id.optionc);
        optiond = findViewById(R.id.optiond);
        ans = findViewById(R.id.detailAns);

        Intent intent = getIntent();
        question = (Question) intent.getSerializableExtra("question");

        tvquestion.setText(question.Question);
        optiona.setText(question.oA);
        optionb.setText(question.oB);
        optionc.setText(question.oC);
        optiond.setText(question.oD);
        ans.setText(question.ans);

    }
}
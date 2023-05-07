package com.example.quizapp;

import static com.example.quizapp.SplashActivity.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 50;
    ProgressBar progressBar;
    List<Question> allQuestionList;
    Question question;
    int index = 0;
    TextView cardquestion, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn, BackBtn;

    Exam exam;

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber;
    public int checkAns;

    TextView txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


//        allQuestionList = QuestionActivity.getData();
//        mPageNumber = getArguments().getInt(ARG_PAGE);
//        checkAns = getArguments().getInt(ARG_CHECKANSWER);

        AlertDialog.Builder b = new AlertDialog.Builder(this);


        Hooks();

        allQuestionList = new ArrayList<>();
       //Collections.shuffle(listOfQ, new Random(20));
       // Collections.shuffle(listOfQ);
        for (int i=0;i< 20 ;i++){
            allQuestionList.add(listOfQ.get(i));
        }
        question = allQuestionList.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOB.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOC.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOD.setBackgroundColor(getResources().getColor(R.color.bg));

        nextBtn.setClickable(false);

        setAllData();

        Intent intent = getIntent();
        exam = (Exam) intent.getSerializableExtra("exam");


        countDownTimer = new CountDownTimer(50000,1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
//                Dialog dialog = new Dialog(DashboardActivity.this, R.style.Dialog);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//                dialog.setContentView(R.layout.time_out_dialog);
//
//                dialog.findViewById(R.id.btn_tryagain).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent= new Intent(DashboardActivity.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                dialog.show();


                LayoutInflater inflater = getLayoutInflater();
                View alberLayout = inflater.inflate(R.layout.time_out_dialog,null);
                //dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                final TextView txtTimeOut = (TextView) alberLayout.findViewById(R.id.txtTimeOut);
                final ImageView icTime = (ImageView) alberLayout.findViewById(R.id.icTime);
                final LinearLayout btn_try = (LinearLayout) alberLayout.findViewById(R.id.btn_tryagain);

                b.setView(alberLayout);

//                b.setMessage("Opps Time Out...");
//                b.setIcon(R.drawable.ic_baseline_timer_24);
//                b.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent intent= new Intent(DashboardActivity.this, SplashActivity.class);
//                        startActivity(intent);
//                    }
//                });

                btn_try.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(QuestionActivity.this, QuestionActivity.class);
                        startActivity(intent);
                    }
                });


                AlertDialog al = b.create();

                al.show();


            }
        }.start();
    }


    private void setAllData() {
        cardquestion.setText(question.getQuestion());
        optiona.setText(question.getoA());
        optionb.setText(question.getoB());
        optionc.setText(question.getoC());
        optiond.setText(question.getoD());
//        timerValue = 20;
//        countDownTimer.cancel();
//        countDownTimer.start();
    }

    private void Hooks() {
        progressBar = findViewById(R.id.quiz_timer);
        cardquestion = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optiona);
        optionb = findViewById(R.id.card_optionb);
        optionc = findViewById(R.id.card_optionc);
        optiond = findViewById(R.id.card_optiond);

        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);

        nextBtn = findViewById(R.id.nextBtn);
        BackBtn = findViewById(R.id.BackBtn);

        BackBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
               startActivity(intent);
           }
       }
        );

    }



    public void Correct(CardView cardView){

        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                index++;
                question = listOfQ.get(index);
                resetColor();
                setAllData();
                enableButton();
            }

        });



    }
    public void Wrong(CardView cardOA){

        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongCount++;
                if(index<listOfQ.size()-1){
                    index++;
                    question = listOfQ.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                }else {
                    GameWon();
                }
            }
        });


    }

    private void GameWon() {
        Intent intent = new Intent(QuestionActivity.this, WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        intent.putExtra("exam",exam);
        startActivity(intent);
    }

    public  void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }
    public  void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }
    public  void  resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOB.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOC.setBackgroundColor(getResources().getColor(R.color.bg));
        cardOD.setBackgroundColor(getResources().getColor(R.color.bg));
    }



    public void OptionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);

        if (question.getoA().equals(question.getAns()))
        {
            cardOA.setBackgroundColor(getResources().getColor(R.color.green));

            if (index<listOfQ.size()-1){
                Correct(cardOA);

//                index++;
//                modelClass=listOfQ.get(index);
//                setAllData();
//                resetColor();
            }
            else {
                GameWon();
            }
        }
        else {
            cardOA.setBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOA);
        }

    }

    public void OptionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);

        if (question.getoB().equals(question.getAns()))
        {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));

            if (index<listOfQ.size()-1){
                Correct(cardOB);
//                index++;
//                modelClass=listOfQ.get(index);
//                setAllData();
//                resetColor(); cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));
            }
            else {
                GameWon();
            }
        }
        else {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOB);
        }
    }

    public void OptionCClick(View view) {
        disableButton();
        nextBtn.setClickable(true);

        if (question.getoC().equals(question.getAns()))
        {
            cardOC.setBackgroundColor(getResources().getColor(R.color.green));

            if (index<listOfQ.size()-1){
                Correct(cardOC);
//                index++;
//                modelClass=listOfQ.get(index);
//                setAllData();
//                resetColor();
            }
            else {
                GameWon();
            }
        }
        else {
            cardOC.setBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOC);
        }

    }

    public void OptionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);

        if (question.getoD().equals(question.getAns()))
        {
            cardOD.setBackgroundColor(getResources().getColor(R.color.green));

            if (index<listOfQ.size()-1){
                Correct(cardOD);
//                index++;
//                modelClass=listOfQ.get(index);
//                setAllData();
//                resetColor();
            }
            else {
                GameWon();
            }
        }
        else {
            cardOD.setBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOD);

        }

    }

}
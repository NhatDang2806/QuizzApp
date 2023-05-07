package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

   public static ArrayList<Question> listOfQ;
   DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        listOfQ = new ArrayList<>();

        Query databaseReference = FirebaseDatabase.getInstance().getReference().child("Question").limitToLast(20);
      //  Query databaseReference= FirebaseDatabase.getInstance().getReference("Question");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Question question = dataSnapshot.getValue(Question.class);
                    listOfQ.add(question);
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        listOfQ.add(new ModelClass("abcd", "argv","brbhr","ceted","dgrtsz","argv"));
//        listOfQ.add(new ModelClass("srhsefdca", "a1242","b","c","d","a1242"));
//        listOfQ.add(new ModelClass("wsdcwdev", "afgbf","b","c","d","afgbf"));
//        listOfQ.add(new ModelClass("rgbsv", "a2343","b","c","d","d"));
//        listOfQ.add(new ModelClass("egvearga", "adbhsr","b","c","d","adbhsr"));
//        listOfQ.add(new ModelClass("13423647", "a2432","b","c","d","b"));
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        },3000);

    }
}
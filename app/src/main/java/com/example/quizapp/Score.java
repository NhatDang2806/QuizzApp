package com.example.quizapp;

public class Score extends Exam{

    String id;
    Exam exam;
    String score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public Score(){

    }

    public Score(String id,String name,String score){
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public Score(String id, Exam exam, String score) {
        this.id = id;
        this.exam = exam;
        this.score = score;
    }

    @Override
    public String toString() {
        return  "Score{" +
                "id='" + id +
                ", name='" + name +
                ", score='" + score + '\'' +
                '}';
    }
}


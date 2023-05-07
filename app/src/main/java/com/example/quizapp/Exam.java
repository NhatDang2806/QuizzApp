package com.example.quizapp;

import java.io.Serializable;

public class Exam implements Serializable, Comparable<Exam>{
    String name;
    String image;

    public Exam(){

    }

    public Exam(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return  "Exam{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public int compareTo(Exam exam) {
        return this.getName().compareTo(exam.getName());
    }
}

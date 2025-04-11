package com.example.algorithms_android_app;
public class Algorithm {
    private String title;
    private String difficulty;
    public Algorithm(String title, String difficulty) {
        this.title = title;
        this.difficulty = difficulty;
    }

    public String getTitle() { return title; }
    public String getDifficulty() { return difficulty; }
}
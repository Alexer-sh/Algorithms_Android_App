package com.example.algorithms_android_app;
import java.io.Serializable;
public class Algorithm implements Serializable{
    private String title;
    private String difficulty;
    public Algorithm(String title, String difficulty) {
        this.title = title;
        this.difficulty = difficulty;
    }
    private String description;
    private String codeSnippet;
    public String getTitle() { return title; }
    public String getDifficulty() { return difficulty; }
    public String getDescription(){ return description; }

    public String getCodeSnippet() {return codeSnippet; }
}
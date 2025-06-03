package com.example.algorithms_android_app;
import java.io.Serializable;
public class Algorithm implements Serializable{
    private String title;
    private String difficulty;
    private String description;
    private String codeSnippet;
    public Algorithm(String title, String difficulty, String description,String codeSnippet) {
        this.title = title;
        this.difficulty = difficulty;
        this.description = description;
        this.codeSnippet = codeSnippet;
    }

    public String getTitle() { return title; }
    public String getDifficulty() { return difficulty; }
    public String getDescription(){ return description; }

    public String getCodeSnippet() {return codeSnippet; }
}
package com.example.algorithms_android_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// AlgorithmsListActivity.java
public class AlgorithmsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 колонки

        // Пример данных
        List<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new Algorithm("Бинарный поиск", "Easy"));
        algorithms.add(new Algorithm("Сортировка пузырьком", "Medium"));
        algorithms.add(new Algorithm("Быстрая сортировка", "Hard"));
        algorithms.add(new Algorithm("Поиск в глубину", "Medium"));

        AlgorithmAdapter adapter = new AlgorithmAdapter(algorithms);
        recyclerView.setAdapter(adapter);
    }
}
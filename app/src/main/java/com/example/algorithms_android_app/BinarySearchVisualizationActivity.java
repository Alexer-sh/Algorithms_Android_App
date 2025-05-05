package com.example.algorithms_android_app;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// BinarySearchVisualizationActivity.java
public class BinarySearchVisualizationActivity extends AppCompatActivity {
    private VisualizationView visualizationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_search_visualization);

        // Инициализация компонентов
        visualizationView = findViewById(R.id.visualization_view);
        Button nextStepButton = findViewById(R.id.next_step_button);

        // Статичные данные для визуализации
        int[] array = {2, 5, 8, 12, 16, 23, 38, 45, 72, 91};
        int target = 23;
        visualizationView.setArray(array);
        visualizationView.setTarget(target);

        // Обработчик кнопки
        nextStepButton.setOnClickListener(v -> visualizationView.nextStep());
    }
}
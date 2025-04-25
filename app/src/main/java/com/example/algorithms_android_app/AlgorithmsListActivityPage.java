package com.example.algorithms_android_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmsListActivityPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //Приношу глубочайшие извинения за такой хардкод, потом всё в нормальной бд лежать будет. Это временный варик.
        List<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new Algorithm("Бинарный поиск", "Easy","Бинарный поиск — это алгоритм для быстрого поиска элемента в отсортированном массиве.\n" +
                "\n" +
                "Он работает так:\n" +
                "\n" +
                "Сравнивает искомое число с серединным элементом массива.\n" +
                "\n" +
                "Если элемент найден — возвращает его позицию.\n" +
                "\n" +
                "Если искомое число меньше — поиск продолжается в левой половине массива.\n" +
                "\n" +
                "Если больше — в правой половине.\n" +
                "\n" +
                "Повторяет процесс, пока элемент не найден или не останется ни одного элемента."));
        algorithms.add(new Algorithm("Сортировка пузырьком", "Medium",""));
        algorithms.add(new Algorithm("Быстрая сортировка", "Hard",""));
        algorithms.add(new Algorithm("Поиск в глубину", "Medium",""));
        algorithms.get(0).codeSnippet =
                "def binary_search(arr, target):\n" +
                        "    left = 0\n" +
                        "    right = len(arr) - 1\n" +
                        "\n" +
                        "    while left <= right:\n" +
                        "        mid = (left + right) // 2\n" +
                        "        if arr[mid] == target:\n" +
                        "            return mid  # нашли элемент\n" +
                        "        elif arr[mid] < target:\n" +
                        "            left = mid + 1\n" +
                        "        else:\n" +
                        "            right = mid - 1\n" +
                        "\n" +
                        "    return -1  # элемент не найден\n" +
                        "\n" +
                        "# Пример использования:\n" +
                        "arr = [1, 3, 5, 7, 9, 11]\n" +
                        "target = 7\n" +
                        "print(binary_search(arr, target))  # Вывод: 3";
        AlgorithmAdapter adapter = new AlgorithmAdapter(algorithms);
        recyclerView.setAdapter(adapter);
    }
}
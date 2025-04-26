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
        algorithms.add(new Algorithm("Сортировка пузырьком", "Medium",
                "Сортировка пузырьком — это простой алгоритм сортировки, который многократно проходит по списку,\n" +
                        "сравнивает пары соседних элементов и меняет их местами, если они идут в неправильном порядке.\n" +
                        "\n" +
                        "Алгоритм работает так:\n" +
                        "\n" +
                        "Проходится по массиву несколько раз.\n" +
                        "\n" +
                        "На каждой итерации самые большие элементы \"всплывают\" в конец массива.\n" +
                        "\n" +
                        "Каждый проход уменьшает область поиска, так как крайние элементы уже отсортированы.\n" +
                        "\n" +
                        "Повторяет процесс до тех пор, пока массив не будет отсортирован."
        ));

        algorithms.add(new Algorithm("Быстрая сортировка", "Hard",
                "Быстрая сортировка — это эффективный алгоритм сортировки, основанный на принципе \"разделяй и властвуй\".\n" +
                        "\n" +
                        "Алгоритм работает так:\n" +
                        "\n" +
                        "Выбирает опорный элемент из массива.\n" +
                        "\n" +
                        "Разделяет остальные элементы на две части: меньше опорного и больше опорного.\n" +
                        "\n" +
                        "Рекурсивно применяет те же действия к каждой части.\n" +
                        "\n" +
                        "Сортировка завершается, когда части становятся пустыми или содержат один элемент."
        ));

        algorithms.add(new Algorithm("Поиск в глубину (DFS)", "Medium",
                "Поиск в глубину (DFS) — это алгоритм обхода графа или дерева, который исследует как можно дальше по каждому пути.\n" +
                        "\n" +
                        "Алгоритм работает так:\n" +
                        "\n" +
                        "Начинает с выбранной вершины.\n" +
                        "\n" +
                        "Идёт в глубину, переходя к соседним непосещённым вершинам.\n" +
                        "\n" +
                        "Если переходит в тупик, возвращается назад и продолжает обход.\n" +
                        "\n" +
                        "Повторяет процесс, пока все вершины не будут посещены."
        ));
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
        algorithms.get(1).codeSnippet =
                "def bubble_sort(arr):\n" +
                        "    n = len(arr)\n" +
                        "    for i in range(n):\n" +
                        "        for j in range(0, n - i - 1):\n" +
                        "            if arr[j] > arr[j + 1]:\n" +
                        "                arr[j], arr[j + 1] = arr[j + 1], arr[j]\n" +
                        "\n" +
                        "# Пример использования:\n" +
                        "arr = [64, 34, 25, 12, 22, 11, 90]\n" +
                        "bubble_sort(arr)\n" +
                        "print(arr)  # Вывод: [11, 12, 22, 25, 34, 64, 90]";
        algorithms.get(2).codeSnippet =
                "def quick_sort(arr):\n" +
                        "    if len(arr) <= 1:\n" +
                        "        return arr\n" +
                        "    pivot = arr[len(arr) // 2]\n" +
                        "    left = [x for x in arr if x < pivot]\n" +
                        "    middle = [x for x in arr if x == pivot]\n" +
                        "    right = [x for x in arr if x > pivot]\n" +
                        "    return quick_sort(left) + middle + quick_sort(right)\n" +
                        "\n" +
                        "# Пример использования:\n" +
                        "arr = [3, 6, 8, 10, 1, 2, 1]\n" +
                        "print(quick_sort(arr))  # Вывод: [1, 1, 2, 3, 6, 8, 10]";
        algorithms.get(3).codeSnippet =
                "def dfs(graph, start, visited=None):\n" +
                        "    if visited is None:\n" +
                        "        visited = set()\n" +
                        "    visited.add(start)\n" +
                        "    print(start)\n" +
                        "    for neighbor in graph[start]:\n" +
                        "        if neighbor not in visited:\n" +
                        "            dfs(graph, neighbor, visited)\n" +
                        "\n" +
                        "# Пример использования:\n" +
                        "graph = {\n" +
                        "    'A': ['B', 'C'],\n" +
                        "    'B': ['D', 'E'],\n" +
                        "    'C': ['F'],\n" +
                        "    'D': [],\n" +
                        "    'E': ['F'],\n" +
                        "    'F': []\n" +
                        "}\n" +
                        "dfs(graph, 'A')  # Вывод: A B D E F C";

        AlgorithmAdapter adapter = new AlgorithmAdapter(algorithms);
        recyclerView.setAdapter(adapter);
    }
}
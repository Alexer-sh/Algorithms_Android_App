package com.example.algorithms_android_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "algorithms.db";
    private static final int DB_VERSION = 6;    //Если что-то меняли в бд, то делаем +1. Технологии! И не хардкод (почти)

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS algorithms (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, difficulty TEXT, description TEXT, code TEXT)");

        insertAlgorithm(db, "Бинарный поиск", "Easy",
                "Бинарный поиск — это алгоритм для быстрого поиска элемента в отсортированном массиве.\n\n" +
                        "Он работает так:\n\n" +
                        "Сравнивает искомое число с серединным элементом массива.\n\n" +
                        "Если элемент найден — возвращает его позицию.\n\n" +
                        "Если искомое число меньше — поиск продолжается в левой половине массива.\n\n" +
                        "Если больше — в правой половине.\n\n" +
                        "Повторяет процесс, пока элемент не найден или не останется ни одного элемента.",
                "def binary_search(arr, target):\n" +
                        "    left = 0\n" +
                        "    right = len(arr) - 1\n\n" +
                        "    while left <= right:\n" +
                        "        mid = (left + right) // 2\n" +
                        "        if arr[mid] == target:\n" +
                        "            return mid\n" +
                        "        elif arr[mid] < target:\n" +
                        "            left = mid + 1\n" +
                        "        else:\n" +
                        "            right = mid - 1\n\n" +
                        "    return -1\n\n" +
                        "arr = [1, 3, 5, 7, 9, 11]\n" +
                        "target = 7\n" +
                        "print(binary_search(arr, target))  # Вывод: 3");


        insertAlgorithm(db, "Сортировка пузырьком", "Medium",
                "Сортировка пузырьком — это простой алгоритм сортировки, который многократно проходит по списку,\n" +
                        "сравнивает пары соседних элементов и меняет их местами, если они идут в неправильном порядке.\n\n" +
                        "Алгоритм работает так:\n\n" +
                        "Проходится по массиву несколько раз.\n\n" +
                        "На каждой итерации самые большие элементы 'всплывают' в конец массива.\n\n" +
                        "Каждый проход уменьшает область поиска, так как крайние элементы уже отсортированы.\n\n" +
                        "Повторяет процесс до тех пор, пока массив не будет отсортирован.",
                "def bubble_sort(arr):\n" +
                        "    n = len(arr)\n" +
                        "    for i in range(n):\n" +
                        "        for j in range(0, n - i - 1):\n" +
                        "            if arr[j] > arr[j + 1]:\n" +
                        "                arr[j], arr[j + 1] = arr[j + 1], arr[j]\n\n" +
                        "arr = [64, 34, 25, 12, 22, 11, 90]\n" +
                        "bubble_sort(arr)\n" +
                        "print(arr)  # Вывод: [11, 12, 22, 25, 34, 64, 90]");

        insertAlgorithm(db, "Быстрая сортировка", "Hard",
                "Быстрая сортировка — это эффективный алгоритм сортировки, основанный на принципе 'разделяй и властвуй'.\n\n" +
                        "Алгоритм работает так:\n\n" +
                        "Выбирает опорный элемент из массива.\n\n" +
                        "Разделяет остальные элементы на две части: меньше опорного и больше опорного.\n\n" +
                        "Рекурсивно применяет те же действия к каждой части.\n\n" +
                        "Сортировка завершается, когда части становятся пустыми или содержат один элемент.",
                "def quick_sort(arr):\n" +
                        "    if len(arr) <= 1:\n" +
                        "        return arr\n" +
                        "    pivot = arr[len(arr) // 2]\n" +
                        "    left = [x for x in arr if x < pivot]\n" +
                        "    middle = [x for x in arr if x == pivot]\n" +
                        "    right = [x for x in arr if x > pivot]\n" +
                        "    return quick_sort(left) + middle + quick_sort(right)\n\n" +
                        "arr = [3, 6, 8, 10, 1, 2, 1]\n" +
                        "print(quick_sort(arr))  # Вывод: [1, 1, 2, 3, 6, 8, 10]");

        insertAlgorithm(db, "Поиск в глубину (DFS)", "Medium",
                "Поиск в глубину (DFS) — это алгоритм обхода графа или дерева, который исследует как можно дальше по каждому пути.\n\n" +
                        "Алгоритм работает так:\n\n" +
                        "Начинает с выбранной вершины.\n\n" +
                        "Идёт в глубину, переходя к соседним непосещённым вершинам.\n\n" +
                        "Если переходит в тупик, возвращается назад и продолжает обход.\n\n" +
                        "Повторяет процесс, пока все вершины не будут посещены.",
                "def dfs(graph, start, visited=None):\n" +
                        "    if visited is None:\n" +
                        "        visited = set()\n" +
                        "    visited.add(start)\n" +
                        "    print(start)\n" +
                        "    for neighbor in graph[start]:\n" +
                        "        if neighbor not in visited:\n" +
                        "            dfs(graph, neighbor, visited)\n\n" +
                        "graph = {\n" +
                        "    'A': ['B', 'C'],\n" +
                        "    'B': ['D', 'E'],\n" +
                        "    'C': ['F'],\n" +
                        "    'D': [],\n" +
                        "    'E': ['F'],\n" +
                        "    'F': []\n" +
                        "}\n" +
                        "dfs(graph, 'A')  # Вывод: A B D E F C");
    }

    private void insertAlgorithm(SQLiteDatabase db, String title, String difficulty, String description, String code) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("difficulty", difficulty);
        values.put("description", description);
        values.put("code", code);
        db.insert("algorithms", null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS algorithms");
        onCreate(db);
    }
}

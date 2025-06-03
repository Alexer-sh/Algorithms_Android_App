package com.example.algorithms_android_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Algorithm> algorithms = new ArrayList<>();

        // И никакого хардкода, только БД
        Cursor cursor = db.rawQuery("SELECT title, difficulty, description, code FROM algorithms", null);
        while (cursor.moveToNext()) {
            Algorithm alg = new Algorithm(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            algorithms.add(alg);
        }
        cursor.close();
        db.close();

        AlgorithmAdapter adapter = new AlgorithmAdapter(algorithms);
        recyclerView.setAdapter(adapter);
    }
}

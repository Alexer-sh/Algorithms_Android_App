package com.example.algorithms_android_app;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlgorithmAdapter extends RecyclerView.Adapter<AlgorithmAdapter.ViewHolder> {
    private final List<Algorithm> algorithms;

    public AlgorithmAdapter(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTextView;  // Добавляем final
        final TextView difficultyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);  // Идентификаторы должны совпадать с item_algorithm.xml
            difficultyTextView = itemView.findViewById(R.id.difficultyTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_algorithm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Algorithm algorithm = algorithms.get(position);
        holder.titleTextView.setText(algorithm.getTitle());
        holder.difficultyTextView.setText("Сложность: " + algorithm.getDifficulty());

        holder.itemView.setOnClickListener(v -> {
            AlgorithmDetailWindow fragment = AlgorithmDetailWindow.newInstance(algorithm);

            // Показываем как диалог
            FragmentManager fm = ((AppCompatActivity)v.getContext()).getSupportFragmentManager();
            fragment.show(fm, "algorithm_detail");
        });
    }
    @Override
    public int getItemCount() {
        return algorithms.size();
    }

}
package com.example.algorithms_android_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.io.Console;
import java.io.Serializable;

public class AlgorithmDetailWindow extends DialogFragment {
    private static final String ARG_ALGORITHM = "algorithm";

    public static AlgorithmDetailWindow newInstance(Algorithm algorithm) {
        AlgorithmDetailWindow fragment = new AlgorithmDetailWindow();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ALGORITHM, (Serializable) algorithm);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//Тупо распиливание на объекты для взаимодействия с интерфейсом
        View view = inflater.inflate(R.layout.algorithm_detail_window, container, false);

        Algorithm algorithm = (Algorithm) getArguments().getSerializable(ARG_ALGORITHM);

        TextView title = view.findViewById(R.id.detail_title);
        TextView description = view.findViewById(R.id.detail_description);
        description.setText(algorithm.getDescription());
        ImageView arrowIcon = view.findViewById(R.id.arrow_icon);
        LinearLayout descriptionHeader = view.findViewById(R.id.description_header);

        title.setText(algorithm.getTitle());
        Button showCodeButton = view.findViewById(R.id.show_code_button);
        showCodeButton.setOnClickListener(v -> {
            AlgorithmCodePage dialog = AlgorithmCodePage.newInstance(
                    algorithm.getTitle(),
                    algorithm.getCodeSnippet()
            );
            dialog.show(getParentFragmentManager(), "code_dialog");
        });
        view.findViewById(R.id.close_button).setOnClickListener(v -> dismiss());

        // Обработчик для выпадающего описания
        descriptionHeader.setOnClickListener(v -> {
            if (description.getVisibility() == View.VISIBLE) {
                description.setVisibility(View.GONE);
                arrowIcon.setImageResource(R.drawable.ic_arrow_down);
            } else {
                description.setVisibility(View.VISIBLE);
                arrowIcon.setImageResource(R.drawable.ic_arrow_up);
            }
        });
        Button btnVisualize = view.findViewById(R.id.visualize_button);
        btnVisualize.setOnClickListener(v -> {
            if (algorithm.getTitle().toLowerCase().contains("бинарный")) {
                Intent intent = new Intent(getActivity(), BinarySearchVisualizationActivity.class);
                startActivity(intent);
                dismiss();
            } else {
                 Toast.makeText(getActivity(), "Визуализация недоступна для этого алгоритма", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Настраиваем размер окна
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(
                    (int)(getResources().getDisplayMetrics().widthPixels * 0.85),
                    (int)(getResources().getDisplayMetrics().heightPixels * 0.7)
            );
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
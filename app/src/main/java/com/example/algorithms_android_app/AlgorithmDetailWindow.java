package com.example.algorithms_android_app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Надуваем макет
        View view = inflater.inflate(R.layout.algorithm_detail_window, container, false);

        Algorithm algorithm = (Algorithm) getArguments().getSerializable(ARG_ALGORITHM);

        TextView title = view.findViewById(R.id.detail_title);
        TextView description = view.findViewById(R.id.detail_description);
        TextView codeExample = view.findViewById(R.id.detail_code);

        title.setText(algorithm.getTitle());
        description.setText(algorithm.getDescription());
        codeExample.setText(algorithm.getCodeSnippet());
        view.findViewById(R.id.close_button).setOnClickListener(v -> dismiss());
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
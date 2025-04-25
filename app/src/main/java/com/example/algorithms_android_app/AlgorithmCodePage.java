package com.example.algorithms_android_app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class AlgorithmCodePage extends DialogFragment {

    private static final String ARG_CODE = "code";
    private static final String ARG_TITLE = "title";

    public static AlgorithmCodePage newInstance(String title, String code) {
        AlgorithmCodePage fragment = new AlgorithmCodePage();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CODE, code);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.algorithm_code_page, container, false);

        String title = getArguments().getString(ARG_TITLE);
        String code = getArguments().getString(ARG_CODE);

        TextView titleView = view.findViewById(R.id.code_title);
        TextView codeView = view.findViewById(R.id.code_content);
        Button copyButton = view.findViewById(R.id.copy_button);
        Button closeButton = view.findViewById(R.id.close_button);

        titleView.setText(title);
        codeView.setText(code);

        copyButton.setOnClickListener(v -> copyToClipboard(code));
        closeButton.setOnClickListener(v -> dismiss());

        return view;
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Код алгоритма", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(requireContext(), "Код скопирован!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        //Размеры окна и масштаб текста
        Window window = getDialog().getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

            window.setLayout(
                    (int)(metrics.widthPixels * 0.9),
                    (int)(metrics.heightPixels * 0.7)
            );
            TextView codeView = getView().findViewById(R.id.code_content);
            codeView.post(() -> {
                if (codeView.getLineCount() > 0) {
                    int textWidth = (int) codeView.getPaint().measureText(
                            codeView.getText().toString().split("\n")[0]);

                    if (textWidth > metrics.widthPixels * 0.8) {
                        codeView.setHorizontallyScrolling(false);
                    }
                }
            });
        }
    }
}
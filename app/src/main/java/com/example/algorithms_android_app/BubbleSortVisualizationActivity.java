package com.example.algorithms_android_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class BubbleSortVisualizationActivity extends AppCompatActivity {

    private int[] array = {10, 7, 3, 15, 9, 2, 13, 6, 8, 5};
    private int i = 0, j = 0;
    private boolean swapped = false;

    private LinearLayout container;
    private HorizontalScrollView scrollView;
    private Button startSortButton, closeButton, editInputButton;
    private SeekBar speedSeekBar;
    private TextView speedLabel;

    private float speedMultiplier = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort_visualization);

        container = findViewById(R.id.bars_container);
        scrollView = findViewById(R.id.scroll_view);
        startSortButton = findViewById(R.id.start_sort_button);
        closeButton = findViewById(R.id.close_button);
        speedSeekBar = findViewById(R.id.speed_seekbar);
        speedLabel = findViewById(R.id.speed_label);
        editInputButton = findViewById(R.id.edit_input_button);

        renderBars();

        startSortButton.setOnClickListener(v -> {
            startSortButton.setEnabled(false);
            editInputButton.setVisibility(View.GONE);
            i = 0;
            j = 0;
            swapped = false;
            nextStep();
        });

        closeButton.setOnClickListener(v -> finish());

        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedMultiplier = mapProgressToSpeed(progress);
                speedLabel.setText(String.format("Скорость: %.2fx", speedMultiplier));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        editInputButton.setOnClickListener(v -> showEditDialog());
    }

    private float mapProgressToSpeed(int progress) {
        return 0.25f + (4f - 0.25f) * progress / 15f;
    }

    private void renderBars() {
        container.removeAllViews();

        int maxVal = Arrays.stream(array).max().orElse(1);
        int minVal = Arrays.stream(array).min().orElse(0);

        for (int k = 0; k < array.length; k++) {
            LinearLayout barLayout = new LinearLayout(this);
            barLayout.setOrientation(LinearLayout.VERTICAL);
            barLayout.setGravity(Gravity.BOTTOM);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    dpToPx(25), dpToPx(200));
            layoutParams.setMargins(dpToPx(4), 0, dpToPx(4), 0);
            barLayout.setLayoutParams(layoutParams);

            float normalized = 0.1f + 0.9f * (array[k] - minVal) / (float)(maxVal - minVal);
            int barHeight = (int)(normalized * dpToPx(180));

            View bar = new View(this);
            LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(dpToPx(20), barHeight);
            bar.setLayoutParams(barParams);

            if ((k == j || k == j + 1) && i < array.length) {
                bar.setBackgroundColor(0xFFFF4081);
            } else {
                bar.setBackgroundColor(0xFF3F51B5);
            }

            TextView label = new TextView(this);
            label.setText(String.valueOf(array[k]));
            label.setGravity(Gravity.CENTER_HORIZONTAL);

            barLayout.addView(bar);
            barLayout.addView(label);

            container.addView(barLayout);
        }

        int scrollToX = j * (dpToPx(25) + dpToPx(8));
        if (scrollToX > scrollView.getScrollX() + scrollView.getWidth()) {
            scrollView.smoothScrollTo(scrollToX, 0);
        }
    }

    private void nextStep() {
        if (i < array.length - 1) {
            if (j < array.length - i - 1) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                j++;
                renderBars();
                container.postDelayed(this::nextStep, (long)(300 / speedMultiplier));
            } else {
                if (!swapped) {
                    Toast.makeText(this, "Сортировка завершена!", Toast.LENGTH_SHORT).show();
                    startSortButton.setEnabled(true);
                    editInputButton.setVisibility(View.VISIBLE);
                } else {
                    swapped = false;
                    j = 0;
                    i++;
                    renderBars();
                    container.postDelayed(this::nextStep, (long)(300 / speedMultiplier));
                }
            }
        } else {
            Toast.makeText(this, "Сортировка завершена!", Toast.LENGTH_SHORT).show();
            startSortButton.setEnabled(true);
            editInputButton.setVisibility(View.VISIBLE);
        }
    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Введите размер массива");

        EditText sizeInput = new EditText(this);
        sizeInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        sizeInput.setHint("Размер массива");
        builder.setView(sizeInput);

        builder.setPositiveButton("Далее", (dialog, which) -> {
            String sizeStr = sizeInput.getText().toString();
            if (sizeStr.isEmpty()) {
                Toast.makeText(this, "Введите размер массива", Toast.LENGTH_SHORT).show();
                return;
            }
            int size = Integer.parseInt(sizeStr);
            if (size <= 0 || size > 50) {
                Toast.makeText(this, "Размер должен быть от 1 до 50", Toast.LENGTH_SHORT).show();
                return;
            }
            showArrayInputDialog(size);
        });

        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

    private void showArrayInputDialog(int size) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Введите элементы массива");

        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        EditText[] inputs = new EditText[size];
        for (int k = 0; k < size; k++) {
            EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
            input.setEms(5);
            input.setHint("[" + k + "]");
            layout.addView(input);
            inputs[k] = input;
        }
        scrollView.addView(layout);

        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.addView(scrollView);

        builder.setView(wrapper);

        builder.setPositiveButton("Применить", (dialog, which) -> {
            int[] newArr = new int[size];
            for (int k = 0; k < size; k++) {
                String val = inputs[k].getText().toString();
                if (val.isEmpty()) {
                    newArr[k] = 0;
                } else {
                    try {
                        newArr[k] = Integer.parseInt(val);
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Некорректный ввод: " + val, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            array = newArr;
            i = 0; j = 0;
            renderBars();
            startSortButton.setEnabled(true);
            editInputButton.setVisibility(View.GONE);
        });

        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}

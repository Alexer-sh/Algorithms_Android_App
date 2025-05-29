package com.example.algorithms_android_app;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
    private Button startSortButton, closeButton;
    private SeekBar speedSeekBar;
    private TextView speedLabel;

    private float speedMultiplier = 1.0f; // 1x скорость по умолчанию

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

        renderBars();

        startSortButton.setOnClickListener(v -> {
            startSortButton.setEnabled(false);
            i = 0;
            j = 0;
            swapped = false;
            nextStep();
        });

        closeButton.setOnClickListener(v -> finish());

        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // max = 15, mapping 0..15 -> speed 0.25x..4x (логика ниже)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedMultiplier = mapProgressToSpeed(progress);
                speedLabel.setText(String.format("Скорость: %.2fx", speedMultiplier));
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private float mapProgressToSpeed(int progress) {
        // Линейно маппим прогресс (0..15) в скорость 0.25..4
        // Чтобы 0 -> 0.25, 15 -> 4
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
                bar.setBackgroundColor(0xFFFF4081); // выделение розовым
            } else {
                bar.setBackgroundColor(0xFF3F51B5); // синий цвет
            }

            TextView label = new TextView(this);
            label.setText(String.valueOf(array[k]));
            label.setGravity(Gravity.CENTER_HORIZONTAL);

            barLayout.addView(bar);
            barLayout.addView(label);

            container.addView(barLayout);
        }

        // Прокручиваем, если надо
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
        }
    }

    private int dpToPx(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density);
    }
}

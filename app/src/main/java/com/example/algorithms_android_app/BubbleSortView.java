package com.example.algorithms_android_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BubbleSortView extends View {
    private int[] array;
    private Paint paintBar, paintText, paintHighlight;
    private int i = 0;

    public BubbleSortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintBar = new Paint();
        paintBar.setColor(Color.BLUE);
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(28f);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintHighlight = new Paint();
        paintHighlight.setColor(Color.RED);
    }

    // Рисовалка массива для сортировки (по сути ваще для любой, а не только конкретно этой)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array == null) return;

        int height = getHeight();
        int barWidth = 40;
        int spacing = 10;
        int max = 0;
        for (int value : array) max = Math.max(max, value);

        for (int k = 0; k < array.length; k++) {
            float ratio = (float) array[k] / max;
            float barHeight = ratio * (height * 0.6f);

            float left = k * (barWidth + spacing);
            float top = height - barHeight - 60;
            float right = left + barWidth;
            float bottom = height - 60;

            Paint currentPaint = (k == i || k == i + 1) ? paintHighlight : paintBar;
            canvas.drawRect(left, top, right, bottom, currentPaint);
            canvas.drawText(String.valueOf(array[k]), left + barWidth / 2, height - 20, paintText);
        }

        int totalWidth = array.length * (barWidth + spacing);
        getLayoutParams().width = totalWidth;
        requestLayout();
    }
}
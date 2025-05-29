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
    private int i = 0, j = 0;
    private boolean sorted = false;

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

    public void setData(int[] data) {
        this.array = data.clone();
        i = 0;
        j = 0;
        sorted = false;
        post(stepRunnable);
    }

    private final Runnable stepRunnable = new Runnable() {
        @Override
        public void run() {
            if (sorted || array == null) return;
            if (i < array.length - 1) {
                if (j < array.length - i - 1) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                    j++;
                } else {
                    j = 0;
                    i++;
                }
                invalidate();
                postDelayed(this, 500);
            } else {
                sorted = true;
                invalidate();
            }
        }
    };

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

            Paint currentPaint = (k == j || k == j + 1) ? paintHighlight : paintBar;
            canvas.drawRect(left, top, right, bottom, currentPaint);
            canvas.drawText(String.valueOf(array[k]), left + barWidth / 2, height - 20, paintText);
        }

        int totalWidth = array.length * (barWidth + spacing);
        getLayoutParams().width = totalWidth;
        requestLayout();
    }
}
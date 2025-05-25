package com.example.algorithms_android_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class VisualizationView extends View {
    private int[] array;
    private int target;
    private int low, high, mid;
    private boolean found;
    private final Paint paint = new Paint();
    private final TextPaint textPaint = new TextPaint();
    private final TextPaint labelPaint = new TextPaint();

    private static class StepState {
        int low, high, mid;
        boolean found;
        StepState(int l, int h, int m, boolean f) {
            low = l; high = h; mid = m; found = f;
        }
    }
    private final List<StepState> steps = new ArrayList<>();

    private static final int CELL_SIZE = 100;
    private static final int VERTICAL_SPACING = 50;
    private static final int TOP_OFFSET = 100;

    public VisualizationView(Context context) {
        super(context);
        init();
    }

    public VisualizationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(35);
        labelPaint.setTextAlign(Paint.Align.CENTER);
        setHorizontalScrollBarEnabled(true);
        setVerticalScrollBarEnabled(true);
    }

    @Override
    protected int computeHorizontalScrollRange() {
        if (array == null) return super.computeHorizontalScrollRange();
        return array.length * (CELL_SIZE + 10);
    }

    @Override
    protected int computeVerticalScrollRange() {
        int count = steps.size();
        int height = TOP_OFFSET + count * (CELL_SIZE + VERTICAL_SPACING) + (found ? 50 : 0);
        return height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = computeHorizontalScrollRange();
        int height = computeVerticalScrollRange();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array == null || steps.isEmpty()) return;

        float stepHeight = CELL_SIZE + VERTICAL_SPACING;
        for (int s = 0; s < steps.size(); s++) {
            StepState st = steps.get(s);
            float y = TOP_OFFSET + s * stepHeight;
            for (int i = 0; i < array.length; i++) {
                if (i == st.mid) paint.setColor(Color.RED);
                else if (i == st.low) paint.setColor(Color.GREEN);
                else if (i == st.high) paint.setColor(Color.BLUE);
                else paint.setColor(Color.GRAY);

                float left = i * (CELL_SIZE + 10);
                canvas.drawRect(left, y, left + CELL_SIZE, y + CELL_SIZE, paint);
                canvas.drawText(String.valueOf(array[i]), left + CELL_SIZE / 2, y + CELL_SIZE / 2 + 15, textPaint);

                float labelY = y + CELL_SIZE + 20;
                if (i == st.low)  canvas.drawText("low", left + CELL_SIZE / 2, labelY, labelPaint);
                if (i == st.mid)  canvas.drawText("mid", left + CELL_SIZE / 2, labelY, labelPaint);
                if (i == st.high) canvas.drawText("high", left + CELL_SIZE / 2, labelY, labelPaint);
            }
        }

        if (found) {
            String msg = "Элемент " + target + " найден в массиве с индексом " + mid;
            float yMsg = TOP_OFFSET + steps.size() * (CELL_SIZE + VERTICAL_SPACING) + 30;
            canvas.drawText(msg, computeHorizontalScrollRange() / 2f, yMsg, textPaint);
        }
    }

    // 👇 Этот метод теперь возвращает boolean
    public boolean nextStep() {
        if (low > high || found) return true; // Поиск завершён

        mid = (low + high) / 2;
        if (array[mid] == target) found = true;
        else if (array[mid] < target) low = mid + 1;
        else high = mid - 1;

        steps.add(new StepState(low, high, mid, found));
        requestLayout();
        invalidate();

        return (low > high || found); // true, если поиск завершён
    }
    public boolean isFound() {
        return found;
    }


    // 👇 Этот метод ничего не возвращает, инициализирует всё
    public void setData(int[] array, int target) {
        this.array = array;
        this.target = target;
        low = 0;
        high = array.length - 1;
        found = false;
        steps.clear();
        mid = (low + high) / 2;
        steps.add(new StepState(low, high, mid, false));
        requestLayout();
        invalidate();
    }
}

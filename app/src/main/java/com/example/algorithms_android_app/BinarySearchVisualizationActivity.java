package com.example.algorithms_android_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BinarySearchVisualizationActivity extends AppCompatActivity {

    private VisualizationView visualizationView;
    private Button nextStepButton, editInputButton;
    private TextView targetValueText;
    private int[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};  //Базовый пример
    private int target = 7;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_search_visualization);

        visualizationView = findViewById(R.id.visualization_view);
        nextStepButton = findViewById(R.id.next_step_button);
        editInputButton = findViewById(R.id.edit_input_button);
        targetValueText = findViewById(R.id.target_value_text);

        visualizationView.setData(array, target);
        targetValueText.setText("Ищем элемент: " + target);
        closeButton = findViewById(R.id.close_button);

        closeButton.setOnClickListener(v -> finish());
        nextStepButton.setOnClickListener(v -> {
            boolean isFinished = visualizationView.nextStep();
            if (isFinished) {
                editInputButton.setVisibility(View.VISIBLE);
                if (!visualizationView.isFound()) {
                    targetValueText.setText("Элемент " + target + " не найден!");
                }
            }
        });


        editInputButton.setOnClickListener(v -> showEditDialog());
    }
    // Методы для изменения параметров и массива поиска
    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Введите размер массива");

        EditText sizeInput = new EditText(this);
        sizeInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(sizeInput);

        builder.setPositiveButton("Далее", (dialog, which) -> {
            int size = Integer.parseInt(sizeInput.getText().toString());
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

        EditText[] arrayInputs = new EditText[size];
        for (int i = 0; i < size; i++) {
            EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
            input.setHint("[" + i + "]");
            input.setEms(4);
            layout.addView(input);
            arrayInputs[i] = input;
        }
        scrollView.addView(layout);

        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.addView(scrollView);

        EditText targetInput = new EditText(this);
        targetInput.setHint("Введите искомый элемент");
        targetInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        wrapper.addView(targetInput);

        builder.setView(wrapper);

        builder.setPositiveButton("Применить", (dialog, which) -> {
            int[] newArray = new int[size];
            for (int i = 0; i < size; i++) {
                String val = arrayInputs[i].getText().toString();
                newArray[i] = val.isEmpty() ? 0 : Integer.parseInt(val);
            }

            //  Защита от идиота
            for (int i = 1; i < size; i++) {
                if (newArray[i] < newArray[i - 1]) {
                    new AlertDialog.Builder(this)
                            .setTitle("Ошибка")
                            .setMessage("Массив должен быть строго возрастающим!")
                            .setPositiveButton("Ок", null)
                            .show();
                    return;
                }
            }

            String targetVal = targetInput.getText().toString();
            int newTarget = targetVal.isEmpty() ? 0 : Integer.parseInt(targetVal);

            this.array = newArray;
            this.target = newTarget;

            visualizationView.setData(array, target);
            targetValueText.setText("Ищем элемент: " + target);
            editInputButton.setVisibility(View.GONE);
        });

        builder.setNegativeButton("Отмена", null);
        builder.show();
    }

}

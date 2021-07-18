package com.example.hw_calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numText;
    private CalculateLogic calculator;
    private final static String KEY_LOGIC = "Calculator";
    private final static Integer DEFAULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Button switchLayout = findViewById(R.id.switchLayout);
        Button switch_layout_back = findViewById(R.id.switch_layout_back);
        int[] numberIds = new int[]{

                R.id.button7,
                R.id.button8,
                R.id.button9,

                R.id.button4,
                R.id.button5,
                R.id.button6,

                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button0,
                R.id.doubleZero,
                R.id.backspace,
                R.id.zero

        };

        int[] operation = new int[]{
                R.id.separate,
                R.id.multi,
                R.id.minus,
                R.id.plus,
                R.id.equally
        };

        numText = findViewById(R.id.numText);
        calculator = new CalculateLogic();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                numText.setText(calculator.getText());
            }
        };
        View.OnClickListener actionButtonClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                numText.setText(calculator.getText());
            }
        };




        for (int numberId : numberIds) {
            findViewById(numberId).setOnClickListener(numberButtonClickListener);
        }
        for (int value : operation) {
            findViewById(value).setOnClickListener(actionButtonClickListener);
        }



        switchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.setContentView(R.layout.activity_constrait_calculate);
            }
        });



    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(KEY_LOGIC, calculator);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        numText.setText(calculator.getText());
        calculator = (CalculateLogic) instanceState.getSerializable(KEY_LOGIC);

    }
}

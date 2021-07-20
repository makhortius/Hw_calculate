package com.example.hw_calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends BaseActivity {
    TextView numText;
    private CalculateLogic calculator;
    private final static String KEY_LOGIC = "Calculator";
    private final static Integer DEFAULT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


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

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                numText.setText(calculator.getText());
            }
        };
        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
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

        Button button= findViewById(R.id.switchLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.setContentView(R.layout.activity_settings);
                initThemeChooser();
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
        calculator = (CalculateLogic) instanceState.getSerializable(KEY_LOGIC);
        numText.setText(calculator.getText());

    }


    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMyTheme),
                MyTheme);
        initRadioButton(findViewById(R.id.radioButtonNightMode),
                NightMode);
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction),
                AppThemeLight);
        initRadioButton(findViewById(R.id.radioButtonMaterialDark),
                AppThemeDark);
        RadioGroup rg = findViewById(R.id.radioButtons);
    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }
}

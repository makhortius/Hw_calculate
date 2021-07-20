package com.example.hw_calculate;

import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.Serializable;

public class CalculateLogic implements Serializable {

    private int firstArg;
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder();
    private int actionSelected;
    private State state;

    private enum State {
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public CalculateLogic() {state = State.firstArgInput;}

    public void onNumPressed(int buttonId) {

        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 10) {
            switch (buttonId) {
                case R.id.button0:
                    inputStr.append("0");
                    break;
                case R.id.button1:
                    inputStr.append("1");
                    break;
                case R.id.button2:
                    inputStr.append("2");
                    break;
                case R.id.button3:
                    inputStr.append("3");
                    break;
                case R.id.button4:
                    inputStr.append("4");
                    break;
                case R.id.button5:
                    inputStr.append("5");
                    break;
                case R.id.button6:
                    inputStr.append("6");
                    break;
                case R.id.button7:
                    inputStr.append("7");
                    break;
                case R.id.button8:
                    inputStr.append("8");
                    break;
                case R.id.button9:
                    inputStr.append("9");
                    break;
                case R.id.doubleZero:
                    inputStr.append("00");
                    break;
                case R.id.backspace:
                    if (inputStr.length() > 0){
                    inputStr.deleteCharAt(inputStr.length() - 1);}
                    else {inputStr.setLength(0);}
                    break;
                case R.id.zero:
                    inputStr.setLength(0);
                    state = State.firstArgInput;
                    break;
            }
        }
    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equally && state == State.secondArgInput) {
            secondArg = Integer.parseInt(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);

            switch (actionSelected) {
                case R.id.separate:
                    if (secondArg == 0) {
                        inputStr.append("error");
                        break;
                    } else {
                        inputStr.append(firstArg / secondArg);
                    }
                    break;
                case R.id.plus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.multi:
                    inputStr.append(firstArg * secondArg);
                    break;


            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.secondArgInput;
            inputStr.setLength(0);

            switch (actionId) {
                case R.id.separate:
                    actionSelected = R.id.separate;
                    break;
                case R.id.plus:
                    actionSelected = R.id.plus;
                    break;
                case R.id.minus:
                    actionSelected = R.id.minus;
                    break;
                case R.id.multi:
                    actionSelected = R.id.multi;
                    break;

            }
        }
    }


    public String getText() {
        return inputStr.toString();
    }
}


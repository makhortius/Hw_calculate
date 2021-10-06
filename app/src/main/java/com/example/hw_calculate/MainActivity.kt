package com.example.hw_calculate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import com.example.hw_calculate.BaseActivity.Companion.MyTheme
import com.example.hw_calculate.MainActivity
import com.example.hw_calculate.BaseActivity.Companion.NightMode as NightMode1

class MainActivity : BaseActivity() {
    private var numText: TextView? = null
    private var calculator: CalculateLogic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)
        val numberIds = intArrayOf(
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
        )
        val operation = intArrayOf(
                R.id.separate,
                R.id.multi,
                R.id.minus,
                R.id.plus,
                R.id.equally
        )
        numText = findViewById(R.id.numText)
        calculator = CalculateLogic()
        val numberButtonClickListener = View.OnClickListener { v ->
            calculator!!.onNumPressed(v.id)
            numText!!.setText(calculator!!.text)
        }
        val actionButtonClickListener = View.OnClickListener { v ->
            calculator!!.onActionPressed(v.id)
            numText!!.setText(calculator!!.text)
        }
        for (numberId in numberIds) {
            findViewById<View>(numberId).setOnClickListener(numberButtonClickListener)
        }
        for (value in operation) {
            findViewById<View>(value).setOnClickListener(actionButtonClickListener)
        }
        val button = findViewById<Button>(R.id.switchLayout)
        button.setOnClickListener {
            this@MainActivity.setContentView(R.layout.activity_settings)
            initThemeChooser()
        }
    }

    public override fun onSaveInstanceState(instanceState: Bundle) {
        super.onSaveInstanceState(instanceState)
        instanceState.putSerializable(KEY_LOGIC, calculator)
    }

    override fun onRestoreInstanceState(instanceState: Bundle) {
        super.onRestoreInstanceState(instanceState)
        calculator = instanceState.getSerializable(KEY_LOGIC) as CalculateLogic?
        numText!!.text = calculator!!.text
    }

    private fun initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonMyTheme),
                MyTheme)
        initRadioButton(findViewById(R.id.radioButtonNightMode),
                NightMode1)
        initRadioButton(findViewById(R.id.radioButtonMaterialLightDarkAction),
                AppThemeLight)
        initRadioButton(findViewById(R.id.radioButtonMaterialDark),
                AppThemeDark)
        val rg = findViewById<RadioGroup>(R.id.radioButtons)
    }

    private fun initRadioButton(button: View, codeStyle: Int) {
        button.setOnClickListener {
            setAppTheme(codeStyle)
            recreate()
        }
    }

    companion object {
        private const val KEY_LOGIC = "Calculator"
        private const val DEFAULT = 0
    }
}
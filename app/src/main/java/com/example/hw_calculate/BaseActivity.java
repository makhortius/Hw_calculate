package com.example.hw_calculate;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    // Имя настроек
    private static final String NameSharedPreference = "LOGIN";

    // Имя параметра в настройках
    private static final String AppTheme = "APP_THEME";

    protected static final int MyTheme = 0;
    protected static final int NightMode = 1;
    protected static final int AppThemeLight = 2;
    protected static final int AppThemeDark = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.MyTheme));

    }

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр «тема»
    protected int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case AppThemeLight:
                return R.style.AppThemeLight;
            case NightMode:
                return R.style.NightMode;
            case AppThemeDark:
                return R.style.AppThemeDark;
            default:
                return R.style.MyTheme;
        }
    }
}


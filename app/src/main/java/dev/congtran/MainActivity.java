package dev.congtran;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String sPREFERENCE_KEY = "dev.congtran";
    private static String sBG_COLOR_KEY = "BG_COLOR";
    private static String sTEXT_COLOR_KEY = "TXT_COLOR";

    private SharedPreferences mSharedPreferences;
    private FrameLayout mFrameLayoutMain;
    private TextView mTextViewHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(sPREFERENCE_KEY, Context.MODE_PRIVATE);

        mFrameLayoutMain = (FrameLayout) findViewById(R.id.frame_layout_main);
        mTextViewHello = (TextView) findViewById(R.id.tv_hello);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        setBackgroundColor(getColor(sBG_COLOR_KEY, Color.WHITE));
        setTextColor(getColor(sTEXT_COLOR_KEY, Color.DKGRAY));
    }


    @Override
    public void onClick(View view) {
        int bgColor = getRandomHexColor();
        int txtColor = getRandomHexColor();

        setBackgroundColor(bgColor);
        setTextColor(txtColor);

        saveColor(sBG_COLOR_KEY, bgColor);
        saveColor(sTEXT_COLOR_KEY, txtColor);
    }

    private int getRandomHexColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void saveColor(String key, int color) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, color);
        editor.commit();
    }

    private int getColor(String key, int defaultColor) {
        return mSharedPreferences.getInt(key, defaultColor);
    }

    private void setBackgroundColor(int color) {
        mFrameLayoutMain.setBackgroundColor(color);
    }

    private void setTextColor(int color) {
        mTextViewHello.setTextColor(color);
    }
}
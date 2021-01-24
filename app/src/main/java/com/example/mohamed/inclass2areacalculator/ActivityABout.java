package com.example.mohamed.inclass2areacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityABout extends AppCompatActivity {

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("Area Calculator");
    }
}

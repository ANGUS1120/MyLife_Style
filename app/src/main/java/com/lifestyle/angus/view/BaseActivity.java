package com.lifestyle.angus.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lifestyle.angus.life_style.R;
import com.lifestyle.angus.util.UncaughtException;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        UncaughtException mUncaughtException = UncaughtException.getInstance();
        mUncaughtException.init(getApplicationContext());
    }
}

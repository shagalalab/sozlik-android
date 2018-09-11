package com.shagalalab.sozlik.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.helper.LocaleHelper;

/**
 * Created by QAREKEN on 4/8/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}

package com.shagalalab.sozlik.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.MainActivity;
import com.shagalalab.sozlik.model.GSONHelper;
import com.shagalalab.sozlik.model.SharedPrefsManager;
import com.shagalalab.sozlik.model.SozlikDatabase;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        GSONHelper gsonHelper = new GSONHelper(this);
        SharedPrefsManager prefsManager = new SharedPrefsManager(this);
        SozlikDatabase database = SozlikDatabase.getSozlikDatabase(this);

        super.onCreate(savedInstanceState);
        SplashPresenter presenter = new SplashPresenter(this, gsonHelper, prefsManager, database);
        presenter.startSplash();
    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

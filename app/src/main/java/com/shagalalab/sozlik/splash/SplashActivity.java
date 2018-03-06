package com.shagalalab.sozlik.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.MainActivity;
import com.shagalalab.sozlik.model.GsonHelper;
import com.shagalalab.sozlik.model.SharedPrefsHelper;
import com.shagalalab.sozlik.model.SozlikDatabase;

public class SplashActivity extends AppCompatActivity implements SplashView {

    private GsonHelper gsonHelper;
    private SharedPrefsHelper prefsHelper;
    private SozlikDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        gsonHelper = new GsonHelper(this, "sozlik.json");
        prefsHelper = new SharedPrefsHelper(this);
        database = SozlikDatabase.getSozlikDatabase(this);

        super.onCreate(savedInstanceState);
        SplashPresenter presenter = new SplashPresenter(this, gsonHelper, prefsHelper, database);
        presenter.startSplash();
    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

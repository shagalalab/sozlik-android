package com.shagalalab.sozlik.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.MainActivity;
import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.model.SozlikDatabase;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GsonHelper gsonHelper = new GsonHelper(this, "sozlik.json");
        SharedPrefsHelper prefsHelper = new SharedPrefsHelper(this);
        SozlikDatabase database = SozlikDatabase.getSozlikDatabase(this);

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

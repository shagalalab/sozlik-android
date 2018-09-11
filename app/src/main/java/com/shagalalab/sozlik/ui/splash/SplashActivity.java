package com.shagalalab.sozlik.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.ui.MainActivity;
import com.shagalalab.sozlik.SozlikApp;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SozlikApp) getApplication()).getComponent().inject(this);

        presenter.setView(this);
        presenter.startSplash();
    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

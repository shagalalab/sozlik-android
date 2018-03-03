package com.shagalalab.sozlik.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shagalalab.sozlik.MainActivity;

/**
 * Created by Manas on 03.03.18.
 *
 */

public class SplashActivity extends AppCompatActivity implements SplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SplashPresenter(this);
        presenter.startSplash();

    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

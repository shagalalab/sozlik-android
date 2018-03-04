package com.shagalalab.sozlik.splash;

public class SplashPresenter {

    private final SplashView view;

    public SplashPresenter(SplashView view) {
        this.view = view;
    }

    public void startSplash() {
        view.goToMainScreen();
    }
}

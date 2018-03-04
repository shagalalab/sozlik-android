package com.shagalalab.sozlik.splash;

class SplashPresenter {

    private final SplashView view;

    SplashPresenter(SplashView view) {
        this.view = view;
    }

    void startSplash() {
        view.goToMainScreen();
    }
}

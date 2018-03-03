package com.shagalalab.sozlik.splash;

/**
 * Created by Manas on 03.03.18.
 *
 */

public class SplashPresenter {

    private SplashView view;

    public SplashPresenter(SplashView view) {
        this.view = view;
    }

    public void startSplash(){
        view.goToMainScreen();
    }


}

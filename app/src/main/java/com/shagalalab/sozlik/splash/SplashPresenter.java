package com.shagalalab.sozlik.splash;

import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.model.SozlikDao;

class SplashPresenter {

    private final SplashView view;
    private final GsonHelper gsonHelper;
    private final SharedPrefsHelper prefsManager;
    private final SozlikDao sozlikDao;
    private AppExecutors appExecutors;

    SplashPresenter(SplashView view, GsonHelper gsonHelper, SharedPrefsHelper prefsManager, SozlikDao sozlikDao,
                    AppExecutors appExecutors) {
        this.view = view;
        this.gsonHelper = gsonHelper;
        this.prefsManager = prefsManager;
        this.sozlikDao = sozlikDao;
        this.appExecutors = appExecutors;
    }

    void startSplash() {
        appExecutors.getDiskIo().execute(new Runnable() {
            @Override
            public void run() {
                if (prefsManager.isFirstLaunch()) {
                    sozlikDao.insertToDB(gsonHelper.getListFromLocalAssets());
                    prefsManager.setFirstLaunch();
                }

                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        view.goToMainScreen();
                    }
                });
            }
        });
    }
}

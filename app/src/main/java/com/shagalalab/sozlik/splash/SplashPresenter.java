package com.shagalalab.sozlik.splash;

import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;

class SplashPresenter {

    private final SplashView view;
    private final GsonHelper gsonHelper;
    private final SharedPrefsHelper prefsManager;
    private final SozlikDatabase database;

    SplashPresenter(SplashView view, GsonHelper gsonHelper, SharedPrefsHelper prefsManager, SozlikDatabase database) {
        this.view = view;
        this.gsonHelper = gsonHelper;
        this.prefsManager = prefsManager;
        this.database = database;
    }

    void startSplash() {
        if (prefsManager.isFirstLaunch()) {
            SozlikDao dao = database.sozlikDao();
            dao.insertToDB(gsonHelper.getListFromLocalAssets());
            prefsManager.setFirstLaunch();
        }
        view.goToMainScreen();
    }
}

package com.shagalalab.sozlik.splash;

import android.util.Log;

import com.shagalalab.sozlik.model.GSONHelper;
import com.shagalalab.sozlik.model.SharedPrefsManager;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

class SplashPresenter {

    private final SplashView view;
    private final GSONHelper gsonHelper;
    private final SharedPrefsManager prefsManager;
    private final SozlikDatabase database;

    SplashPresenter(SplashView view, GSONHelper gsonHelper, SharedPrefsManager prefsManager, SozlikDatabase database) {
        this.view = view;
        this.gsonHelper = gsonHelper;
        this.prefsManager = prefsManager;
        this.database = database;
    }

    void startSplash() {
        if (prefsManager.isFirstLaunch()) {
            SozlikDao dao = database.sozlikDao();
            List<SozlikDbModel> list = gsonHelper.modelList();
            Log.d("myLog", "startSplash: " + list.toString());
            dao.insertToDB(list);
            prefsManager.setFirstLaunch(false);
            view.goToMainScreen();
        } else {
            view.goToMainScreen();
        }
    }
}

package com.shagalalab.sozlik.splash;

import com.shagalalab.sozlik.dictionary.autocomplete.WordHolder;
import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

class SplashPresenter {

    private final SplashView view;
    private final GsonHelper gsonHelper;
    private final SharedPrefsHelper prefsManager;
    private final SozlikDao sozlikDao;
    private AppExecutors appExecutors;
    private WordHolder wordHolder;

    SplashPresenter(SplashView view, GsonHelper gsonHelper, SharedPrefsHelper prefsManager, SozlikDao sozlikDao,
                    AppExecutors appExecutors, WordHolder wordHolder) {
        this.view = view;
        this.gsonHelper = gsonHelper;
        this.prefsManager = prefsManager;
        this.sozlikDao = sozlikDao;
        this.appExecutors = appExecutors;
        this.wordHolder = wordHolder;
    }

    void startSplash() {
        appExecutors.getDiskIo().execute(new Runnable() {
            @Override
            public void run() {
                initSozlikData();
                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        view.goToMainScreen();
                    }
                });
            }
        });
    }

        void initSozlikData() {
        if (prefsManager.isFirstLaunch()) {
            sozlikDao.insertToDB(gsonHelper.getListFromLocalAssets());
            prefsManager.setFirstLaunch();
        }
        List<SozlikDbModel> models = sozlikDao.getAllWords();
        wordHolder.setWordList(models);
        wordHolder.setWordMap(models);
    }
}

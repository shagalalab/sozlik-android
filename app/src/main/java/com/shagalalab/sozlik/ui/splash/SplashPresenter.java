package com.shagalalab.sozlik.ui.splash;

import com.shagalalab.sozlik.ui.dictionary.autocomplete.WordHolder;
import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

public class SplashPresenter {

    private SplashView view;
    private GsonHelper gsonHelper;
    private SharedPrefsHelper prefsManager;
    private SozlikDao sozlikDao;
    private AppExecutors appExecutors;
    private WordHolder wordHolder;

    public SplashPresenter(GsonHelper gsonHelper, SharedPrefsHelper prefsManager, SozlikDao sozlikDao,
                    AppExecutors appExecutors, WordHolder wordHolder) {
        this.gsonHelper = gsonHelper;
        this.prefsManager = prefsManager;
        this.sozlikDao = sozlikDao;
        this.appExecutors = appExecutors;
        this.wordHolder = wordHolder;
    }

    public void setView(SplashView view) {
        this.view = view;
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
            sozlikDao.insertToDB(gsonHelper.getQqEnFromLocalAssets());
            prefsManager.setFirstLaunch();
        }
        if (!prefsManager.hasRuQq()) {
            sozlikDao.updateQqEnType();
            sozlikDao.insertToDB(gsonHelper.getRuQqFromLocalAssets());
            prefsManager.setRuQq();
        }
        List<SozlikDbModel> models = sozlikDao.getAllWords();
        wordHolder.setWordMap(models);
    }
}

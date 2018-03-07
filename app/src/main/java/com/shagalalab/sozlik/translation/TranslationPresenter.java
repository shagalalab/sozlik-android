package com.shagalalab.sozlik.translation;

import com.shagalalab.sozlik.model.SozlikDao;

/**
 * Created by manas on 06.03.18.
 */

class TranslationPresenter {

    private TranslationView translationView;
    private SozlikDao sozlikDao;

    TranslationPresenter(TranslationView translationView, SozlikDao sozlikDao) {
        this.translationView = translationView;
        this.sozlikDao = sozlikDao;
    }

    void showWordById(int id) {
        translationView.showWord(sozlikDao.getTranslationById(id).getWord());
    }

    void showTranslationById(int id) {
        translationView.showTranslation(sozlikDao.getTranslationById(id).getTranslation());
    }
}

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

    void getTranslationById(int id) {
        String word = sozlikDao.getTranslationById(id).getWord();
        String translation = sozlikDao.getTranslationById(id).getTranslation();
        if (word != null && translation != null) {
            translationView.showTranslation(word, translation);
        }
    }
}

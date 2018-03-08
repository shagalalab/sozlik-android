package com.shagalalab.sozlik.translation;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

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
        SozlikDbModel model = sozlikDao.getTranslationById(id);
        if (model != null) {
            this.translationView.showWord(model.getWord());
            this.translationView.showTranslation(model.getTranslation());
        }
    }
}

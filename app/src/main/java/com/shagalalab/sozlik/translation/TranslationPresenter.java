package com.shagalalab.sozlik.translation;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

/**
 * Created by manas on 06.03.18.
 */

class TranslationPresenter {

    private TranslationView translationView;
    private SozlikDao sozlikDao;
    private SozlikDbModel model;

    TranslationPresenter(TranslationView translationView, SozlikDao sozlikDao) {
        this.translationView = translationView;
        this.sozlikDao = sozlikDao;
    }

    void getTranslationById(int id) {
        model = sozlikDao.getTranslationById(id);
        if (model != null) {
            translationView.showWord(model.getWord());
            translationView.showTranslation(model.getTranslation());
        }
    }

    void toggleFavorite() {
        if (model != null) {
            boolean isFavourite = !model.isFavourite();
            model.setFavourite(isFavourite);
            sozlikDao.update(model);
            translationView.showFavorite(isFavourite);
        }
    }

    void setFavoriteStatus() {
        if (model != null) {
            translationView.showFavorite(model.isFavourite());
        }
    }

    void shareTranslation() {
        if (model != null) {
            translationView.goToShare(model.getWord(), model.getMessageForShare());
        }
    }

    void setLastAccessed(int id, long time) {
        SozlikDbModel dbModel = sozlikDao.getTranslationById(id);
        if (dbModel != null) {
            dbModel.setLastAccessed(time);
            sozlikDao.update(dbModel);
        }
    }
}


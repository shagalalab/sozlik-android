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

    TranslationPresenter(TranslationView translationView, SozlikDao sozlikDao, SozlikDbModel model) {
        this.translationView = translationView;
        this.sozlikDao = sozlikDao;
        this.model = model;
    }

    void getTranslation() {
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

    void setLastAccessed(long time) {
        if (model != null) {
            model.setLastAccessed(time);
            sozlikDao.update(model);
        }
    }
}


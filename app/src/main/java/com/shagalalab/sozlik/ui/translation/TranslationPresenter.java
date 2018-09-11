package com.shagalalab.sozlik.ui.translation;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

/**
 * Created by manas on 06.03.18.
 */

public class TranslationPresenter {

    private TranslationView translationView;
    private SozlikDao sozlikDao;
    private SozlikDbModel model;

    public TranslationPresenter(SozlikDao sozlikDao) {
        this.sozlikDao = sozlikDao;
    }

    void setModelById(int translationId) {
        this.model = sozlikDao.getTranslationById(translationId);
    }

    void setView(TranslationView translationView) {
        this.translationView = translationView;
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


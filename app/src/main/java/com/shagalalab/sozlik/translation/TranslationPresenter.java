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
            translationView.showWord(model.getWord());
            translationView.showTranslation(model.getTranslation());
        }
    }

    void toggleFavorite(int id) {
        SozlikDbModel dbModel = sozlikDao.getTranslationById(id);
        if (dbModel != null) {
            boolean isFavourite = !dbModel.isFavourite();
            dbModel.setFavourite(isFavourite);
            sozlikDao.updateFavorites(dbModel);
            translationView.showFavorite(isFavourite);
        }
    }

    void isFavorite(int id) {
        SozlikDbModel dbModel = sozlikDao.getTranslationById(id);
        if (dbModel != null) {
            translationView.showFavorite(dbModel.isFavourite());
        }
    }
}

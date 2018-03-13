package com.shagalalab.sozlik.favorites;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 10.03.18.
 */

class FavoritesPresenter {
    private FavoritesView view;
    private SozlikDao sozlikDao;
    private List<SozlikDbModel> list;

    FavoritesPresenter(FavoritesView view, SozlikDao sozlikDao) {
        this.view = view;
        this.sozlikDao = sozlikDao;
    }

    void showFavoritesList() {
        list = sozlikDao.getAllFavorites();
        if (list != null) {
            view.showFavorites(list);
        }
    }

    void deleteFavorite(SozlikDbModel dbModel) {
        if (dbModel != null) {
            dbModel.setFavourite(false);
            sozlikDao.updateFavorites(dbModel);
        }
    }
}
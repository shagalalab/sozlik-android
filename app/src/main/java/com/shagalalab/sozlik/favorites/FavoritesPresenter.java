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

    FavoritesPresenter(FavoritesView view, SozlikDao sozlikDao) {
        this.view = view;
        this.sozlikDao = sozlikDao;
    }

    void showFavoritesList() {
        List<SozlikDbModel> list = sozlikDao.getAllFavorites();
        if (list != null) {
            if (list.isEmpty()) {
                view.hideList();
                view.showEmptyScreen();
            } else {
                view.hideEmptyScreen();
                view.showList();
                view.showFavorites(list);
            }
        }
    }

    void deleteFavorite(SozlikDbModel dbModel) {
        if (dbModel != null) {
            dbModel.setFavourite(false);
            sozlikDao.update(dbModel);
        }
    }
}

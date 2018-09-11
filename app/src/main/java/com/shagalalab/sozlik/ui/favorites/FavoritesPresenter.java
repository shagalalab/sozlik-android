package com.shagalalab.sozlik.ui.favorites;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 10.03.18.
 */

public class FavoritesPresenter {
    private FavoritesView view;
    private SozlikDao sozlikDao;

    public FavoritesPresenter(SozlikDao sozlikDao) {
        this.sozlikDao = sozlikDao;
    }

    public void setView(FavoritesView view) {
        this.view = view;
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

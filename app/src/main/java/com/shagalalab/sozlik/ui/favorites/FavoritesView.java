package com.shagalalab.sozlik.ui.favorites;

import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 10.03.18.
 */

public interface FavoritesView {
    void showFavorites(List<SozlikDbModel> list);
    void showEmptyScreen();
    void hideEmptyScreen();
    void showList();
    void hideList();
}

package com.shagalalab.sozlik.favorites;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 10.03.18.
 */

public interface FavoritesView {
    void showFavorites(List<SozlikDbModel> list);
    void showEmptyScreen();
}

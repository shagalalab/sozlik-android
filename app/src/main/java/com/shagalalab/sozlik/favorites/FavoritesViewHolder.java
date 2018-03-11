package com.shagalalab.sozlik.favorites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

/**
 * Created by manas on 12.03.18.
 */

class FavoritesViewHolder extends RecyclerView.ViewHolder {
    private TextView word;
    private ImageButton favoriteButton;
    private SozlikDbModel model;
    private View favoritesItem;
    private FavoriteAdapterCallback callback;


    FavoritesViewHolder(View itemView, FavoriteAdapterCallback callback) {
        super(itemView);
        this.callback = callback;
        word = itemView.findViewById(R.id.favourite_word);
        favoriteButton = itemView.findViewById(R.id.favourite_button);
        favoritesItem = itemView.findViewById(R.id.favorites_item);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoritesIconClicked();
            }
        });
        favoritesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoritesItemClicked();
            }
        });
    }

    void populateModel(SozlikDbModel model) {
        this.model = model;
        word.setText(model.getWord());
    }


    private void onFavoritesItemClicked() {
        if (callback != null && model != null) {
            callback.onFavoriteItemClicked(model.getId());
        }
    }

    private void onFavoritesIconClicked() {
        if (callback != null && model != null) {
            callback.onFavoriteIconClicked(model);
        }
    }

    interface FavoriteAdapterCallback {
        void onFavoriteItemClicked(int translationId);

        void onFavoriteIconClicked(SozlikDbModel model);
    }
}


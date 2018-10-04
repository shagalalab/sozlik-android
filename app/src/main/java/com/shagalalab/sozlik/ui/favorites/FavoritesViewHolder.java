package com.shagalalab.sozlik.ui.favorites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.data.SozlikDbModel;

/**
 * Created by manas on 12.03.18.
 */

class FavoritesViewHolder extends RecyclerView.ViewHolder {
    private TextView word;
    private SozlikDbModel model;
    private FavoriteAdapterCallback callback;


    FavoritesViewHolder(View itemView, FavoriteAdapterCallback callback) {
        super(itemView);
        this.callback = callback;
        word = itemView.findViewById(R.id.favourite_word);

        ImageButton favoriteButton = itemView.findViewById(R.id.favourite_button);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoritesIconClicked();
            }
        });

        View favoritesItem = itemView.findViewById(R.id.favorites_item);
        favoritesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoritesItemClicked();
            }
        });
    }

    void populateModel(SozlikDbModel model) {
        this.model = model;
        word.setText(model.getNormalizedWord());
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


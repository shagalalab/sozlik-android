package com.shagalalab.sozlik.favorites;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 10.03.18.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
    private List<SozlikDbModel> data;
    private FavoritesViewHolder.FavoriteAdapterCallback callback;

    FavoritesAdapter(List<SozlikDbModel> data, FavoritesViewHolder.FavoriteAdapterCallback callback) {
        this.data = data;
        this.callback = callback;
    }

    public void setData(List<SozlikDbModel> list) {
        if (list != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        return new FavoritesViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        SozlikDbModel dbModel = data.get(position);
        if (dbModel != null) {
            holder.populateModel(dbModel);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

package com.shagalalab.sozlik.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by QAREKEN on 3/10/2018.
 */

public class SuggestionResultsAdapter extends RecyclerView.Adapter<SuggestionViewHolder> {

    private List<SozlikDbModel> data;
    private SuggestionListener suggestionListener;

    SuggestionResultsAdapter(SuggestionListener suggestionListener) {
        this.suggestionListener = suggestionListener;
    }

    void updateItems(List<SozlikDbModel> data) {
        this.data = data;
    }

    @Override
    public SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false);
        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SuggestionViewHolder holder, int position) {
        SozlikDbModel item = data.get(position);
        if (item != null) {
            holder.populateModel(item, suggestionListener);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

}

package com.shagalalab.sozlik.dictionary.suggestion;

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
    private String originalWord = "";

    public SuggestionResultsAdapter(SuggestionListener suggestionListener) {
        this.suggestionListener = suggestionListener;
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
            holder.populateModel(originalWord, item, suggestionListener);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void updateItems(String originalWord, List<SozlikDbModel> data) {
        this.originalWord = originalWord;
        this.data = data;
        notifyDataSetChanged();
    }
}

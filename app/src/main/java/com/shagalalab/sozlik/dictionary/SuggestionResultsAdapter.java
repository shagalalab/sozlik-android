package com.shagalalab.sozlik.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by QAREKEN on 3/10/2018.
 */

public class SuggestionResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SozlikDbModel> data;
    private SuggestionListener suggestionListener;

    SuggestionResultsAdapter(SuggestionListener suggestionListener) {
        this.suggestionListener = suggestionListener;
    }

    void updateItems(List<SozlikDbModel> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final SozlikDbModel item = data.get(position);
        ((SuggestionHolder) holder).word.setText(item.getWord());
        ((SuggestionHolder) holder).word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suggestionListener != null) {
                    suggestionListener.onSuggestionClicked(item.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder {
        private TextView word;

        SuggestionHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.item_suggestion_word);
        }
    }
}

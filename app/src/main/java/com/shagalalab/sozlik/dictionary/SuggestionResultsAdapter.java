package com.shagalalab.sozlik.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 3/10/2018.
 */

public class SuggestionResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SozlikDbModel> data;
    private SuggestionListener suggestionListener;

    public SuggestionResultsAdapter(SuggestionListener suggestionListener) {
        this.suggestionListener = suggestionListener;
    }

    void updateItems(ArrayList<SozlikDbModel> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_words_item, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        ((SuggestionHolder) holder).word.setText(data.get(pos).getWord());
        ((SuggestionHolder) holder).word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suggestionListener.showSuggestionTranslate(data.get(pos).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder {
        private TextView word;

        SuggestionHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.list_item_word);
        }
    }
}

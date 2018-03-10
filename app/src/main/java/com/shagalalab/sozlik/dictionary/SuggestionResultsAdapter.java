package com.shagalalab.sozlik.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 3/10/2018.
 */

public class SuggestionResultsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SozlikDbModel> data;
    private DictionaryView dictionaryView;

    public SuggestionResultsAdapter(ArrayList<SozlikDbModel> data, DictionaryView dictionaryView) {
        this.data = data;
        this.dictionaryView = dictionaryView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_words_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        ((Holder) holder).word.setText(data.get(pos).getWord());
        ((Holder) holder).container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dictionaryView.showTranslation(data.get(pos).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView word;
        private LinearLayout container;
        Holder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.list_item_word);
            container = itemView.findViewById(R.id.list_of_words_item);
        }
    }
}

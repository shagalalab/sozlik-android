package com.shagalalab.sozlik.dictionary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manas on 16.03.18.
 */

public class WordAutoCompleteAdapter extends ArrayAdapter<SozlikDbModel> implements Filterable {

    private final List<SozlikDbModel> models;
    private List<SozlikDbModel> wordResults = new ArrayList<>();
    private TextView word;
    private SuggestionListener listener;

    WordAutoCompleteAdapter(Context context, List<SozlikDbModel> words, SuggestionListener listener) {
        super(context, 0, words);
        this.models = words;
        this.listener = listener;
    }

    void changeWordResults(List<SozlikDbModel> list) {
        wordResults.clear();
        wordResults.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return wordResults.size();
    }

    @Override
    public SozlikDbModel getItem(int position) {
        return wordResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void populateMode(final SozlikDbModel item, final SuggestionListener listener) {
        word.setText(item.getWord());
        if (listener != null) {
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSuggestionClicked(item.getId());
                }
            });
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SozlikDbModel dbModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false);
        }
        word = convertView.findViewById(R.id.item_suggestion_word);
        if (dbModel != null) {
            populateMode(dbModel, listener);
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new WordFilter(this, models);
    }
}

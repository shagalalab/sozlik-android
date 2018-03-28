package com.shagalalab.sozlik.dictionary.autocomplete;

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
import com.shagalalab.sozlik.dictionary.suggestion.SuggestionListener;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manas on 16.03.18.
 */

public class WordAutoCompleteAdapter extends ArrayAdapter<SozlikDbModel> implements Filterable, OnChangeWordListener {

    private final List<SozlikDbModel> models;
    private List<SozlikDbModel> wordResults = new ArrayList<>();
    private TextView word;
    private SuggestionListener listener;
    private LayoutInflater inflater;

    public WordAutoCompleteAdapter(Context context, WordHolder words, SuggestionListener listener) {
        super(context, 0, words.getInstanse(context));
        this.models = words.getInstanse(context);
        this.listener = listener;
        this.inflater = LayoutInflater.from(getContext());
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

    private void populateModel(final SozlikDbModel item, final SuggestionListener listener) {
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
            convertView = inflater.inflate(R.layout.item_suggestion, parent, false);
        }
        word = convertView.findViewById(R.id.item_suggestion_word);
        if (dbModel != null) {
            populateModel(dbModel, listener);
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new WordFilter(this, models);
    }

    @Override
    public void onChangeWordResults(List<SozlikDbModel> list) {
        wordResults.clear();
        wordResults.addAll(list);
        notifyDataSetChanged();
    }
}

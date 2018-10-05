package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by manas on 16.03.18.
 */

public class WordAutoCompleteAdapter extends ArrayAdapter<SozlikDbModel> implements Filterable, OnChangeWordListener {
    private final List<SozlikDbModel> models;
    private List<SozlikDbModel> wordResults = new ArrayList<>();
    private LinearLayout root;
    private TextView word;
    private ImageView flagFrom;
    private ImageView flagTo;
    private AutoCompleteListener listener;
    private LayoutInflater inflater;
    private String originalWord = "";

    public WordAutoCompleteAdapter(Context context, List<SozlikDbModel> models, AutoCompleteListener listener) {
        super(context, 0, models);
        this.models = models;
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

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SozlikDbModel dbModel = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_suggestion, parent, false);
        }
        root = convertView.findViewById(R.id.item_suggestion_parent);
        word = convertView.findViewById(R.id.item_suggestion_word);
        flagFrom = convertView.findViewById(R.id.item_suggestion_flag_from);
        flagTo = convertView.findViewById(R.id.item_suggestion_flag_to);
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
    public void onChangeWordResults(String originalWord, List<SozlikDbModel> list) {
        this.originalWord = originalWord;
        if (list != null && !list.isEmpty()) {
            Collections.sort(list);
            wordResults.clear();
            wordResults.addAll(list);
            notifyDataSetChanged();
        }
    }

    private void populateModel(final SozlikDbModel item, final AutoCompleteListener listener) {
        word.setText(Html.fromHtml(item.getNormalizedWord().replace(originalWord, String.format("<b>%s</b>", originalWord))));
        flagFrom.setImageResource(item.getFromResource());
        flagTo.setImageResource(item.getToResource());
        if (listener != null && root != null) {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAutoCompleteClicked(item);
                }
            });
        }
    }
}

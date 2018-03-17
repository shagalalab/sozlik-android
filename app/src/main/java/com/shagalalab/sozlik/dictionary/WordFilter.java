package com.shagalalab.sozlik.dictionary;

import android.widget.Filter;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by manas on 17.03.18.
 */

public class WordFilter extends Filter {

    private WordAutoCompleteAdapter adapter;
    private List<SozlikDbModel> originalList;
    private List<SozlikDbModel> filteredList;

    WordFilter(WordAutoCompleteAdapter adapter, List<SozlikDbModel> originalList) {
        super();
        this.adapter = adapter;
        this.originalList = originalList;
        this.filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
            for (final SozlikDbModel dbModel : originalList) {
                if (dbModel.getWord().toLowerCase(Locale.ROOT).contains(filterPattern)) {
                    filteredList.add(dbModel);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (adapter != null) {
            adapter.changeWordResults((List) results.values);
        }
    }
}

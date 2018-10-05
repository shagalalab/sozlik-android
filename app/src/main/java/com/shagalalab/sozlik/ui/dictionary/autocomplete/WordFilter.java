package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import android.widget.Filter;

import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by manas on 17.03.18.
 */

public class WordFilter extends Filter {

    private OnChangeWordListener listener;
    private List<SozlikDbModel> originalList;
    private List<SozlikDbModel> filteredList;

    WordFilter(OnChangeWordListener listener, List<SozlikDbModel> originalList) {
        super();
        this.listener = listener;
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
                if (dbModel.getWord().toLowerCase(Locale.ROOT).startsWith(filterPattern)) {
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
        if (listener != null && constraint != null) {
            listener.onChangeWordResults(constraint.toString(), (List) results.values);
        }
    }
}
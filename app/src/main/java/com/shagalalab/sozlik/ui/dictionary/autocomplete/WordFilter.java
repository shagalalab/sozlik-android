package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import android.widget.Filter;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by manas on 17.03.18.
 */

public class WordFilter extends Filter {

    private OnChangeWordListener listener;
    private SozlikDao sozlikDao;
    private List<SozlikDbModel> filteredList;

    WordFilter(OnChangeWordListener listener, SozlikDao sozlikDao) {
        super();
        this.listener = listener;
        this.sozlikDao = sozlikDao;
        this.filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            final String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
            List<SozlikDbModel> suggestions = sozlikDao.getSuggestions(filterPattern + "%");
            if (!suggestions.isEmpty()) {
                filteredList.addAll(suggestions);
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (listener != null && constraint != null && results != null && results.values != null) {
            listener.onChangeWordResults(constraint.toString(), (List) results.values);
        }
    }
}

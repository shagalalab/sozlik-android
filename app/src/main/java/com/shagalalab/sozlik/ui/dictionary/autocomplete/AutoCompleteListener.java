package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import com.shagalalab.sozlik.data.SozlikDbModel;

public interface AutoCompleteListener {
    void onAutoCompleteClicked(SozlikDbModel word);
}

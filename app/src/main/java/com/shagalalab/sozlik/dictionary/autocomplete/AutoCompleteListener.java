package com.shagalalab.sozlik.dictionary.autocomplete;

import com.shagalalab.sozlik.model.SozlikDbModel;

public interface AutoCompleteListener {
    void onAutoCompleteClicked(SozlikDbModel word);
}

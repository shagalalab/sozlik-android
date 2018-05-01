package com.shagalalab.sozlik.dictionary.suggestion;

import com.shagalalab.sozlik.model.SozlikDbModel;

/**
 * Created by QAREKEN on 3/11/2018.
 */

public interface SuggestionListener {
    void onSuggestionClicked(SozlikDbModel word);
}

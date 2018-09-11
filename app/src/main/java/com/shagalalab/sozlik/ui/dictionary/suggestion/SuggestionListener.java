package com.shagalalab.sozlik.ui.dictionary.suggestion;

import com.shagalalab.sozlik.data.SozlikDbModel;

/**
 * Created by QAREKEN on 3/11/2018.
 */

public interface SuggestionListener {
    void onSuggestionClicked(SozlikDbModel word);
}

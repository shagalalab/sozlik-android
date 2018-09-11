package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 18.03.18.
 */

public interface OnChangeWordListener {
    void onChangeWordResults(String originalWord, List<SozlikDbModel> list);
}

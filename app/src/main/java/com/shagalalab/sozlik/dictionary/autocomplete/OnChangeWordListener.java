package com.shagalalab.sozlik.dictionary.autocomplete;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 18.03.18.
 */

public interface OnChangeWordListener {
    void onChangeWordResults(String originalWord, List<SozlikDbModel> list);
}

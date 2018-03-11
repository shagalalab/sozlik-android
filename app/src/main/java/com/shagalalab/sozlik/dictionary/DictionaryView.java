package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by QAREKEN on 3/9/2018.
 */

public interface DictionaryView {
    void showResults(List<SozlikDbModel> listOfResults);
    void showTranslation(int wordId);
    void showMessage(int res);
}

package com.shagalalab.sozlik.ui.dictionary;

import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

/**
 * Created by QAREKEN on 3/9/2018.
 */

public interface DictionaryView {
    void showResults(List<SozlikDbModel> listOfResults);
    void showTranslation(int wordId);
    void showMessage(int res);
    void setMessageVisible();
    void showKeyboardMessage();
    void hideKeyboardMessage();
}

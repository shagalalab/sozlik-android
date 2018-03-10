package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 3/9/2018.
 */

public interface DictionaryView {
    void showResults(ArrayList<SozlikDbModel> listOfResults);
    void showTranslation(int translation);
    void showMessage(String message);
}

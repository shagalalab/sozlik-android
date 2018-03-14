package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;
import java.util.Locale;

/**
 * Created by QAREKEN on 3/8/2018.
 */

class DictionaryPresenter {
    private static final int WORD_MIN_LENGTH = 3;
    private DictionaryView dictionaryView;
    private SozlikDao sozlikDao;
    private SozlikDbModel result;
    private List<SozlikDbModel> listOfResults;

    DictionaryPresenter(DictionaryView dictionaryView, SozlikDao sozlikDao) {
        this.dictionaryView = dictionaryView;
        this.sozlikDao = sozlikDao;
    }

    void search(String word) {
        if (word.isEmpty()) {
            return;
        }
        word = word.toLowerCase(Locale.ROOT);
        result = sozlikDao.getTranslation(word);
        if (result != null) {
            dictionaryView.showTranslation(result.getId());
        } else if (word.length() >= this.WORD_MIN_LENGTH) {
            listOfResults = sozlikDao.getSuggestions('%' + word + '%');
            dictionaryView.showMessage(listOfResults.isEmpty() ? R.string.suggestion_not_found : R.string.suggestion_found);
            dictionaryView.showResults(listOfResults);
        }
    }

}

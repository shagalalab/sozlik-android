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
        } else {
            listOfResults = sozlikDao.getSuggestions('%' + word + '%');
            dictionaryView.showMessage(listOfResults.isEmpty() ? R.string.translation_not_found : R.string.translation_found);
            dictionaryView.showResults(listOfResults);
        }
    }

}

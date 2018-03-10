package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by QAREKEN on 3/8/2018.
 */

class DictionaryPresenter {
    private static final String FOUND = " tabılmadı, bálkim tómendegilerdi izlegen shıǵarsız.";
    private static final String NOT_FOUND = " boyınsha nátiyjeler tabılmadı";
    private static final String WORD_IS_EMPTY = "Hesh qanday sóz kiritpedińiz";
    private DictionaryView dictionaryView;
    private SozlikDao sozlikDao;
    private SozlikDbModel result;
    private List<SozlikDbModel> listOfResults;

    DictionaryPresenter(DictionaryView dictionaryView, SozlikDao sozlikDao) {
        this.dictionaryView = dictionaryView;
        this.sozlikDao = sozlikDao;
    }

    void search(String word) {
        if (Objects.equals(word, "")) {
            dictionaryView.showError(WORD_IS_EMPTY);
            listOfResults = new ArrayList<>();
            dictionaryView.showResults((ArrayList<SozlikDbModel>) listOfResults);
            return;
        }
        word = word.toLowerCase(Locale.ROOT);
        result = sozlikDao.getTranslation(word);
        if (result != null) {
            dictionaryView.showTranslation(result.getId());
        } else {
            listOfResults = sozlikDao.getSuggestions('%' + word + '%');
            dictionaryView.showMessage(listOfResults.isEmpty() ? NOT_FOUND : FOUND);
            dictionaryView.showResults((ArrayList<SozlikDbModel>) listOfResults);
        }
    }

}

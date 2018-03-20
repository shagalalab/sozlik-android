package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.helper.PackageHelper;
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
    private PackageHelper packageHelper;

    DictionaryPresenter(DictionaryView dictionaryView, SozlikDao sozlikDao, PackageHelper packageHelper) {
        this.dictionaryView = dictionaryView;
        this.sozlikDao = sozlikDao;
        this.packageHelper = packageHelper;
    }

    void search(String word) {
        if (word.isEmpty()) {
            return;
        }

        word = word.toLowerCase(Locale.ROOT);
        SozlikDbModel result = sozlikDao.getTranslation(word);

        if (result != null) {
            dictionaryView.showTranslation(result.getId());
        } else if (word.length() >= WORD_MIN_LENGTH) {
            List<SozlikDbModel> listOfResults = sozlikDao.getSuggestions('%' + word + '%');
            dictionaryView.showMessage(listOfResults.isEmpty() ? R.string.suggestion_not_found : R.string.suggestion_found);
            dictionaryView.showResults(listOfResults);
        } else {
            dictionaryView.showMessage(R.string.suggestion_not_found);
        }
    }

    void setKeyboardMessageVisibility() {
        if (packageHelper.isAppInstalled()) {
            dictionaryView.hideKeyboardMessage();
        } else {
            dictionaryView.showKeyboardMessage();
        }
    }
}

package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.dictionary.spellchecker.SpellChecker;
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
    private SpellChecker spellChecker;

    DictionaryPresenter(DictionaryView dictionaryView, SozlikDao sozlikDao, PackageHelper packageHelper, SpellChecker spellChecker) {
        this.dictionaryView = dictionaryView;
        this.sozlikDao = sozlikDao;
        this.packageHelper = packageHelper;
        this.spellChecker = spellChecker;
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
            List<SozlikDbModel> listOfResults = spellChecker.check(word);
            dictionaryView.showMessage(listOfResults.isEmpty() ? R.string.suggestion_not_found : R.string.suggestion_found);
            dictionaryView.setMessageVisible();
            dictionaryView.showResults(listOfResults);
        } else {
            dictionaryView.showMessage(R.string.suggestion_not_found);
            dictionaryView.setMessageVisible();
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

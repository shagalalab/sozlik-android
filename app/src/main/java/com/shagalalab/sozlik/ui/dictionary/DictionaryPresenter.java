package com.shagalalab.sozlik.ui.dictionary;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.ui.dictionary.spellchecker.SpellChecker;
import com.shagalalab.sozlik.helper.PackageHelper;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;
import java.util.Locale;

/**
 * Created by QAREKEN on 3/8/2018.
 */

public class DictionaryPresenter {
    private static final int WORD_MIN_LENGTH = 3;

    private DictionaryView dictionaryView;
    private SozlikDao sozlikDao;
    private PackageHelper packageHelper;
    private SpellChecker spellChecker;

    public DictionaryPresenter(SozlikDao sozlikDao, PackageHelper packageHelper, SpellChecker spellChecker) {
        this.sozlikDao = sozlikDao;
        this.packageHelper = packageHelper;
        this.spellChecker = spellChecker;
    }

    public void setView(DictionaryView dictionaryView) {
        this.dictionaryView = dictionaryView;
    }

    void search(String word) {
        boolean isLatinAlphabet;

        if (word.isEmpty()) {
            return;
        }

        word = word.toLowerCase(Locale.ROOT);

        if (SpellChecker.ALPHABET_LATIN.indexOf(word.charAt(0)) >= 0) {
            isLatinAlphabet = true;
        } else if (SpellChecker.ALPHABET_CYRILLIC.indexOf(word.charAt(0)) >= 0) {
            isLatinAlphabet = false;
        } else {
            dictionaryView.showMessage(R.string.invalid_input);
            dictionaryView.setMessageVisible();
            return;
        }
        SozlikDbModel result = sozlikDao.getTranslation(word);

        if (result != null) {
            dictionaryView.showTranslation(result.getId());
        } else if (word.length() >= WORD_MIN_LENGTH) {
            List<SozlikDbModel> listOfResults = spellChecker.check(word, isLatinAlphabet);
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

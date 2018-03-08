package com.shagalalab.sozlik.dictionary;

/**
 * Created by QAREKEN on 3/8/2018.
 */

class DictionaryPresenter {
    private DictionaryView dictionaryView;

    DictionaryPresenter(DictionaryView dictionaryView) {
        this.dictionaryView = dictionaryView;
    }

    void searchView(String word) {
        dictionaryView.showMessage(word);
    }

}

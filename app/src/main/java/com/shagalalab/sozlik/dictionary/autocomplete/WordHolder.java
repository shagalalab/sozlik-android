package com.shagalalab.sozlik.dictionary.autocomplete;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 28.03.18.
 */

public final class WordHolder {

    private static WordHolder instance;
    private List<SozlikDbModel> wordList;

    private WordHolder() {
    }

    public static WordHolder getInstance() {
        if (instance == null) {
            instance = new WordHolder();
        }
        return instance;
    }

    public List<SozlikDbModel> getWordList() {
        return wordList;
    }

    public void setWordList(List<SozlikDbModel> wordList) {
        this.wordList = wordList;
    }
}

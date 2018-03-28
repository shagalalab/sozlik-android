package com.shagalalab.sozlik.dictionary.autocomplete;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 28.03.18.
 */

public class WordHolder {

    private static WordHolder instance;
    private List<SozlikDbModel> wordList;

    public WordHolder() {
    }

    public static WordHolder getInstance() {
        if (instance == null) {
            instance = new WordHolder();
        }
        return instance;
    }

    List<SozlikDbModel> getWordList() {
        return wordList;
    }

    public void setWordList(List<SozlikDbModel> wordList) {
        this.wordList = wordList;
    }
}

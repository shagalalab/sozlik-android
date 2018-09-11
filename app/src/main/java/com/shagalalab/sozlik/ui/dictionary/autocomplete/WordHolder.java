package com.shagalalab.sozlik.ui.dictionary.autocomplete;

import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manas on 28.03.18.
 */

public final class WordHolder {

    private static WordHolder instance;
    private List<SozlikDbModel> wordList;
    private HashMap<String, Integer> wordHashMap = null;

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

    public Map<String, Integer> getWordMap() {
        return wordHashMap;
    }

    public void setWordMap(List<SozlikDbModel> wordList) {
        wordHashMap = new HashMap<>();
        for (SozlikDbModel w: wordList) {
            wordHashMap.put(w.getWord(), w.getId());
        }
    }
}

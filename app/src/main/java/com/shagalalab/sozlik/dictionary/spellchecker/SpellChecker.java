package com.shagalalab.sozlik.dictionary.spellchecker;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by QAREKEN on 4/18/2018.
 */

public class SpellChecker {
    private static final String ALPHABET = "aábcdefgǵhiıjklmnńoópqrstuúvwxyz";

    private Map<String, Integer> words;
    private SozlikDao sozlikDao;

    public SpellChecker(Map<String, Integer> words, SozlikDao sozlikDao) {
        this.sozlikDao = sozlikDao;
        if (words == null) {
            this.words = new HashMap<>();
        } else {
            this.words = words;
        }
    }

    private static Set<String> edits1(String word) {
        Set<String> edits = new HashSet<>();
        List<SplitWord> splits = SplitWord.allSplits(word);
        for (SplitWord split : splits) {
            String a = split.getPrefix();
            String b = split.getSuffix();
            int lb = b.length();
            if (lb > 0) {
                edits.add(a + b.substring(1)); // delete 1 character
                for (int i = 0; i < ALPHABET.length(); ++i) {
                    edits.add(a + ALPHABET.charAt(i) + b.substring(1)); // replace 1 characters
                }
            }
            if (lb > 1) {
                edits.add(a + b.substring(2)); //delete 2 characters
                edits.add(a + b.charAt(1) + b.charAt(0) + b.substring(2)); // transpose
                for (int i = 0; i < ALPHABET.length(); ++i) {
                    for (int j = 0; j < ALPHABET.length(); j++) {
                        edits.add(a + ALPHABET.charAt(i) + ALPHABET.charAt(j) + b.substring(2)); // replace 2 characters
                    }
                }
            }
            for (int i = 0; i < ALPHABET.length(); ++i) {
                edits.add(a + ALPHABET.charAt(i) + b); // insert
                for (int j = 0; j < ALPHABET.length(); j++) {
                    edits.add(a + ALPHABET.charAt(i) + ALPHABET.charAt(j) + b);
                }
            }
        }
        return edits;
    }

    // We only check for single edits and return empty of none of the possibilities are in the dictionary
    public List<SozlikDbModel> check(String word) {
        List<SozlikDbModel> alternatives = new ArrayList<>();
        SozlikDbModel model;
        word = word.toLowerCase(Locale.ROOT);

        if (words.containsKey(word)) {
            model = sozlikDao.getTranslation(word);
            if (model != null) {
                alternatives.add(model);
            }
            return alternatives;
        }

        Set<String> edits = edits1(word);
        for (String w : edits) {
            if (words.containsKey(w)) {
                model = sozlikDao.getTranslation(w);
                if (model != null) {
                    alternatives.add(model);
                }
            }
        }
        Collections.sort(alternatives, new Comparator<SozlikDbModel>() {
            @Override
            public int compare(SozlikDbModel t1, SozlikDbModel t2) {
                return t1.getWord().compareTo(t2.getWord());
            }
        });
        return alternatives;
    }

}

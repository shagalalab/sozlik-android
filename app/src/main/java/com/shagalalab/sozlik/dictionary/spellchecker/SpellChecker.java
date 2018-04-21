package com.shagalalab.sozlik.dictionary.spellchecker;

import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by QAREKEN on 4/18/2018.
 */

public class SpellChecker {
    public static final String ALPHABET_LATIN = "aábcdefgǵhiıjklmnńoópqrstuúvwxyz";
    public static final String ALPHABET_CYRILLIC = "аәбвгғдеёжзийкқлмнңоөпрстуүўфхҳцчшщъыьэюя";

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

    private static Set<String> edits1(String word, String alphabet) {
        Set<String> edits = new HashSet<>();
        List<SplitWord> splits = SplitWord.allSplits(word);
        for (SplitWord split : splits) {
            String a = split.getPrefix();
            String b = split.getSuffix();
            int lb = b.length();
            if (lb > 0) {
                edits.add(a + b.substring(1)); // delete 1 character
                for (int i = 0; i < alphabet.length(); ++i) {
                    edits.add(a + alphabet.charAt(i) + b.substring(1)); // replace 1 characters
                }
            }
            if (lb > 1) {
                edits.add(a + b.substring(2)); //delete 2 characters
                edits.add(a + b.charAt(1) + b.charAt(0) + b.substring(2)); // transpose
                for (int i = 0; i < alphabet.length(); ++i) {
                    for (int j = 0; j < alphabet.length(); j++) {
                        edits.add(a + alphabet.charAt(i) + alphabet.charAt(j) + b.substring(2)); // replace 2 characters
                    }
                }
            }
            for (int i = 0; i < alphabet.length(); ++i) {
                edits.add(a + alphabet.charAt(i) + b); // insert
                for (int j = 0; j < alphabet.length(); j++) {
                    edits.add(a + alphabet.charAt(i) + alphabet.charAt(j) + b);
                }
            }
        }
        return edits;
    }

    public List<SozlikDbModel> check(String word, boolean isLatin) {
        String alphabet = isLatin ? ALPHABET_LATIN : ALPHABET_CYRILLIC;
        List<SozlikDbModel> alternatives = new ArrayList<>();
        SozlikDbModel model;

        if (words.containsKey(word)) {
            model = sozlikDao.getTranslation(word);
            if (model != null) {
                alternatives.add(model);
            }
            return alternatives;
        }

        Set<String> edits = edits1(word, alphabet);
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

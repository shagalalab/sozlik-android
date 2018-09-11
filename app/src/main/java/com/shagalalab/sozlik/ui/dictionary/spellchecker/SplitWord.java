package com.shagalalab.sozlik.ui.dictionary.spellchecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QAREKEN on 4/18/2018.
 */

public final class SplitWord {
    private String prefix;
    private String suffix;

    private SplitWord(String word, int splitPos) {
        prefix = word.substring(0, splitPos);
        suffix = word.substring(splitPos, word.length());
    }

    String getPrefix() {
        return prefix;
    }

    String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return prefix + "-" + suffix;
    }

    static List<SplitWord> allSplits(String word) {
        List<SplitWord> splits = new ArrayList<>();
        for (int i = 0; i <= word.length(); ++i) {
            splits.add(new SplitWord(word, i));
        }
        return splits;
    }
}

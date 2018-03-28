package com.shagalalab.sozlik.dictionary.autocomplete;

import android.content.Context;

import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 28.03.18.
 */

public class WordHolder {

    private static List<SozlikDbModel> wordList;

    List<SozlikDbModel> getInstanse(Context context) {
        if (wordList == null) {
            wordList = SozlikDatabase.getSozlikDatabase(context).sozlikDao().getAllWords();
        }
        return wordList;
    }

}

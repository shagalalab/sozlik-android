package com.shagalalab.sozlik.dictionary;

import com.shagalalab.sozlik.model.SozlikDbModel;

import java.util.List;

/**
 * Created by manas on 18.03.18.
 */

public interface OnChangeWordListener {
    void onChangeWordResults(List<SozlikDbModel> list);
}

package com.shagalalab.sozlik.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by manas on 05.03.18.
 */

public class SharedPrefsHelper {
    private static final String FILE_NAME = "preferences";
    private static final String IS_FIRST_LAUNCH = "is_first_launch";
    private static final String HAS_RU_QQ = "has_ru_qq";

    private SharedPreferences preferences;

    public SharedPrefsHelper(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setFirstLaunch() {
        preferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply();
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public void setRuQq() {
        preferences.edit().putBoolean(HAS_RU_QQ, true).apply();
    }

    public boolean hasRuQq() {
        return preferences.getBoolean(HAS_RU_QQ, false);
    }

}

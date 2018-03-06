package com.shagalalab.sozlik.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by manas on 05.03.18.
 */

public class SharedPrefsHelper {
    private static final String FILE_NAME = "preferences";
    private static final String IS_FIRST_LAUNCH = "is_first_launch";

    private SharedPreferences preferences;

    public SharedPrefsHelper(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setFirstLaunch(boolean firstLaunch) {
        getEditor().putBoolean(IS_FIRST_LAUNCH, firstLaunch).commit();
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

}

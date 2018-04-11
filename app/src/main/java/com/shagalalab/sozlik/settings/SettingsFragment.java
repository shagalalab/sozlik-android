package com.shagalalab.sozlik.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.shagalalab.sozlik.R;

/**
 * Created by QAREKEN on 3/17/2018.
 */

public class SettingsFragment extends PreferenceFragment {

    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if (getString(R.string.pref_alphabet_key).equals(s)) {
                    LocaleHelper.setLocale(getActivity(), sharedPreferences.getString(s, getString(R.string.pref_alphabet_latin)));
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }
}

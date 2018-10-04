package com.shagalalab.sozlik.di.module;

import android.content.Context;

import com.shagalalab.sozlik.ui.dictionary.autocomplete.WordHolder;
import com.shagalalab.sozlik.ui.dictionary.spellchecker.SpellChecker;
import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.PackageHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.data.SozlikDao;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HelperModule {
    private static final String JSON_FILE_NAME_QQ_EN = "json_file_name_qq_en";
    private static final String JSON_FILE_NAME_RU_QQ = "json_file_name_ru_qq";

    @Provides
    @Named(JSON_FILE_NAME_QQ_EN)
    @Singleton
    public String providesJsonFileNameQqEn() {
        return "sozlik.json";
    }

    @Provides
    @Named(JSON_FILE_NAME_RU_QQ)
    @Singleton
    public String providesJsonFileNameRuQq() {
        return "ruqq.json";
    }

    @Provides
    @Singleton
    GsonHelper providesGsonHelper(Context context, @Named(JSON_FILE_NAME_QQ_EN) String fileNameQqEn,
                                  @Named(JSON_FILE_NAME_RU_QQ) String fileNameRuQq) {
        return new GsonHelper(context, fileNameQqEn, fileNameRuQq);
    }

    @Provides
    @Singleton
    PackageHelper providesPackageHelper(Context context) {
        return new PackageHelper(context);
    }

    @Provides
    @Singleton
    SharedPrefsHelper providesSharedPrefsHelper(Context context) {
        return new SharedPrefsHelper(context);
    }

    @Provides
    @Singleton
    public AppExecutors providesThreadHelper() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    public WordHolder providesWordHolder() {
        return WordHolder.getInstance();
    }

    @Provides
    @Singleton
    public SpellChecker providesSpellChecker(WordHolder wordHolder, SozlikDao sozlikDao) {
        return new SpellChecker(wordHolder.getWordMap(), sozlikDao);
    }
}

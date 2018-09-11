package com.shagalalab.sozlik.di.module;

import com.shagalalab.sozlik.helper.GsonHelper;
import com.shagalalab.sozlik.helper.PackageHelper;
import com.shagalalab.sozlik.helper.SharedPrefsHelper;
import com.shagalalab.sozlik.helper.thread.AppExecutors;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.ui.dictionary.DictionaryPresenter;
import com.shagalalab.sozlik.ui.dictionary.autocomplete.WordHolder;
import com.shagalalab.sozlik.ui.dictionary.spellchecker.SpellChecker;
import com.shagalalab.sozlik.ui.favorites.FavoritesPresenter;
import com.shagalalab.sozlik.ui.splash.SplashPresenter;
import com.shagalalab.sozlik.ui.translation.TranslationPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    SplashPresenter providesSplashPresenter(GsonHelper gsonHelper, SharedPrefsHelper prefsHelper, SozlikDao sozlikDao,
                                            AppExecutors appExecutors, WordHolder wordHolder) {
        return new SplashPresenter(gsonHelper, prefsHelper, sozlikDao, appExecutors, wordHolder);
    }

    @Provides
    @Singleton
    DictionaryPresenter providesDictionaryPresenter(SozlikDao sozlikDao, PackageHelper packageHelper, SpellChecker spellChecker) {
        return new DictionaryPresenter(sozlikDao, packageHelper, spellChecker);
    }

    @Provides
    @Singleton
    FavoritesPresenter providesFavoritesPresenter(SozlikDao sozlikDao) {
        return new FavoritesPresenter(sozlikDao);
    }

    @Provides
    @Singleton
    TranslationPresenter providesTranslationPresenter(SozlikDao sozlikDao) {
        return new TranslationPresenter(sozlikDao);
    }
}

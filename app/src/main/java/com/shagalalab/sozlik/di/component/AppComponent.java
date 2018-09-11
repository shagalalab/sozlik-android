package com.shagalalab.sozlik.di.component;

import com.shagalalab.sozlik.di.module.AppModule;
import com.shagalalab.sozlik.di.module.DataModule;
import com.shagalalab.sozlik.di.module.HelperModule;
import com.shagalalab.sozlik.di.module.PresenterModule;
import com.shagalalab.sozlik.ui.dictionary.DictionaryFragment;
import com.shagalalab.sozlik.ui.favorites.FavoritesFragment;
import com.shagalalab.sozlik.ui.history.HistoryFragment;
import com.shagalalab.sozlik.ui.splash.SplashActivity;
import com.shagalalab.sozlik.ui.translation.TranslationActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, HelperModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(SplashActivity activity);
    void inject(TranslationActivity activity);

    void inject(DictionaryFragment fragment);
    void inject(FavoritesFragment fragment);
    void inject(HistoryFragment fragment);
}

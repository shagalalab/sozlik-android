package com.shagalalab.sozlik.di.module;

import android.content.Context;

import com.shagalalab.sozlik.SozlikApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private SozlikApp app;

    public AppModule(SozlikApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return app.getApplicationContext();
    }
}

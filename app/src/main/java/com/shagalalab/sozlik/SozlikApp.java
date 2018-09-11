package com.shagalalab.sozlik;

import android.app.Application;

import com.shagalalab.sozlik.di.component.AppComponent;
import com.shagalalab.sozlik.di.component.DaggerAppComponent;
import com.shagalalab.sozlik.di.module.AppModule;

public class SozlikApp extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}

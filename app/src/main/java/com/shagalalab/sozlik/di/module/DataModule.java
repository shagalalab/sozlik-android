package com.shagalalab.sozlik.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    private static final String DB_NAME = "db_name";
    private static final String FILE_NAME = "preferences";

    @Provides
    @Named(DB_NAME)
    @Singleton
    public String providesDatabaseName() {
        return "sozlik-database";
    }

    @Provides
    @Singleton
    public SozlikDatabase providesDatabase(Context context, @Named(DB_NAME) String dbName) {
        return Room
            .databaseBuilder(context, SozlikDatabase.class, dbName)
            .allowMainThreadQueries()
            .build();
    }

    @Provides
    @Singleton
    public SozlikDao providesSozlikDao(SozlikDatabase database) {
        return database.sozlikDao();
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

}

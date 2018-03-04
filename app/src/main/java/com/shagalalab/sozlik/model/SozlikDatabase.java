package com.shagalalab.sozlik.model;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Database(entities = {SozlikDbModel.class}, version = 1)
public class SozlikDatabase extends RoomDatabase {

    private static SozlikDatabase instance;

    private SozlikDao sozlikDao;

    public static SozlikDatabase getSozlikDatabase(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), SozlikDatabase.class, "sozlik-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }



    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}

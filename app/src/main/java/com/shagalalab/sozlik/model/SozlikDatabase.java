package com.shagalalab.sozlik.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Database(entities = {SozlikDbModel.class}, version = 1, exportSchema = false)
public abstract class SozlikDatabase extends RoomDatabase {

    private static SozlikDatabase instance;

    public abstract SozlikDao sozlikDao();

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

}

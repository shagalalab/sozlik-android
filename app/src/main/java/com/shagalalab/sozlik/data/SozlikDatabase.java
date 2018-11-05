package com.shagalalab.sozlik.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Database(entities = {SozlikDbModel.class}, version = 2, exportSchema = false)
public abstract class SozlikDatabase extends RoomDatabase {
    public abstract SozlikDao sozlikDao();
}

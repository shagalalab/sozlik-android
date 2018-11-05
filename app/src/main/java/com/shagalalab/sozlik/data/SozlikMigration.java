package com.shagalalab.sozlik.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public final class SozlikMigration {

    private SozlikMigration() {
    }

    public static final Migration MIGRATE_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // add type column for dictionary type [1 is 'qq-en' / 2 is 'ru-qq']
            database.execSQL("ALTER TABLE dictionary ADD COLUMN type INTEGER NOT NULL DEFAULT 0");
        }
    };
}

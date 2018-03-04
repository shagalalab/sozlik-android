package com.shagalalab.sozlik.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Dao
public interface SozlikDao {

    @Query ("SELECT * FROM dictionary WHERE word LIKE :word")
    SozlikDbModel getTranslation(String word);

    @Query ("SELECT * FROM dictionary WHERE id = :id")
    SozlikDbModel getTranslationById(int id);

}

package com.shagalalab.sozlik.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Dao
public interface SozlikDao {

    @Query ("SELECT * FROM SozlikDbModel WHERE word LIKE :word")
    SozlikDbModel getTraslation(String word);

    @Query ("SELECT * FROM SozlikDbModel WHERE id = :id")
    SozlikDbModel getTranslationById(int id);

}
package com.shagalalab.sozlik.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Dao
public interface SozlikDao {

    @Query("SELECT * FROM SozlikEntity WHERE word LIKE :word")
    SozlikEntity getTraslation(String word);

    @Query("SELECT * FROM SozlikEntity WHERE id = :id")
    SozlikEntity getTranslationById(int id);
}
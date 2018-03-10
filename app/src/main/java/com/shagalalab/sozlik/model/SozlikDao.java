package com.shagalalab.sozlik.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Dao
public interface SozlikDao {

    @Query("SELECT * FROM dictionary WHERE word LIKE :word")
    List<SozlikDbModel> getSuggestions(String word);

    @Query("SELECT * FROM dictionary WHERE word = :word")
    SozlikDbModel getTranslation(String word);

    @Query("SELECT * FROM dictionary WHERE id = :id")
    SozlikDbModel getTranslationById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToDB(List<SozlikDbModel> models);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorites(SozlikDbModel sozlikDbModel);

}

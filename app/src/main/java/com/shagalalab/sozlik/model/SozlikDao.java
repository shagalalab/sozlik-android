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

    @Query("SELECT * FROM dictionary WHERE word = :word")
    SozlikDbModel getTranslation(String word);

    @Query("SELECT * FROM dictionary WHERE id = :id")
    SozlikDbModel getTranslationById(int id);

    @Query("SELECT * FROM dictionary WHERE word LIKE :word")
    List<SozlikDbModel> getSuggestions(String word);

    @Query("SELECT * FROM dictionary")
    List<SozlikDbModel> getAllWords();

    @Query("SELECT * FROM dictionary WHERE is_favourite = 1")
    List<SozlikDbModel> getAllFavorites();

    @Query("SELECT * FROM dictionary WHERE last_accessed > 0 ORDER BY last_accessed DESC LIMIT 20")
    List<SozlikDbModel> getHistoryList20();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToDB(List<SozlikDbModel> models);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(SozlikDbModel sozlikDbModel);

}

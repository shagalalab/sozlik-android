package com.shagalalab.sozlik.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.v4.net.ConnectivityManagerCompat;

import java.sql.Date;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Entity(tableName = "dictionary")
public class SozlikEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "translation")
    private String translation;

    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite;

    @ColumnInfo(name = "last_accessed")
    private Date lastAccessed;

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
}
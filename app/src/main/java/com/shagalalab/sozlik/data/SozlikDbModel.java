package com.shagalalab.sozlik.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.shagalalab.sozlik.R;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by QAREKEN on 3/4/2018.
 */
@Entity(tableName = "dictionary")
public class SozlikDbModel implements Comparable<SozlikDbModel> {
    private static final int TYPE_QQ_EN = 1;
    private static final int TYPE_RU_QQ = 2;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "translation")
    private String translation;

    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite;

    @ColumnInfo(name = "last_accessed")
    private long lastAccessed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public String getNormalizedWord() {
        return word.toLowerCase(Locale.getDefault()).replace("//", "");
    }

    public int getFromResource() {
        if (type == TYPE_QQ_EN) {
            return R.drawable.ic_flag_qq;
        } else {
            return R.drawable.ic_flag_ru;
        }
    }

    public int getToResource() {
        if (type == TYPE_RU_QQ) {
            return R.drawable.ic_flag_qq;
        } else {
            return R.drawable.ic_flag_uk;
        }
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

    public long getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(long lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public String getMessageForShare() {
        return String.format("%s%n%s", word, translation.replaceAll("\\<.*?>", ""));
    }

    @Override
    public int compareTo(@NonNull SozlikDbModel o) {
        return this.getNormalizedWord().compareTo(o.getNormalizedWord());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof SozlikDbModel)) {
            return false;
        }

        SozlikDbModel that = (SozlikDbModel) obj;

        return this.id == that.id
            && this.type == that.type
            && this.word.equals(that.word)
            && this.translation.equals(that.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, word, translation);
    }
}

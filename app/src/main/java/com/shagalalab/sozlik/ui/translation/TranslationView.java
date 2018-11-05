package com.shagalalab.sozlik.ui.translation;

/**
 * Created by manas on 06.03.18.
 */

public interface TranslationView {
    void showWord(String word);
    void showTranslation(String translation);
    void setFromFlags(int resource);
    void setToFlags(int resource);
    void showFavorite(Boolean favourite);
    void goToShare(String word, String translation);
}

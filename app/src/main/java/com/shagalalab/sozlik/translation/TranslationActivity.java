package com.shagalalab.sozlik.translation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;

public class TranslationActivity extends AppCompatActivity implements TranslationView {

    private TranslationPresenter presenter;
    private SozlikDao sozlikDao;
    private TextView tvWord;
    private TextView tvTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        tvWord = findViewById(R.id.tv_word);
        tvTranslation = findViewById(R.id.tv_translation);
        sozlikDao = SozlikDatabase.getSozlikDatabase(this).sozlikDao();
        presenter = new TranslationPresenter(this, sozlikDao);
        int translationId = getIntent().getIntExtra("translationId", -1);
        presenter.showWordById(translationId);
        presenter.showTranslationById(translationId);
    }

    @Override
    public void showWord(String word) {
        tvWord.setText(word);
    }

    @Override
    public void showTranslation(String translation) {
        tvTranslation.setText(translation);
    }
}

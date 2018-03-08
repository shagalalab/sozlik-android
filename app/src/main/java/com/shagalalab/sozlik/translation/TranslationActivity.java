package com.shagalalab.sozlik.translation;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;

import java.util.Random;

public class TranslationActivity extends AppCompatActivity implements TranslationView {

    private TranslationPresenter presenter;
    private SozlikDao sozlikDao;
    private TextView word;
    private TextView translation;
    private Random random;
    private final int maxValue = 15182;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        word = findViewById(R.id.word);
        translation = findViewById(R.id.translation);
        sozlikDao = SozlikDatabase.getSozlikDatabase(this).sozlikDao();
        presenter = new TranslationPresenter(this, sozlikDao);
        random = new Random();
        int randomNumber = random.nextInt(maxValue) + 1;
        int translationId = getIntent().getIntExtra("translationId", randomNumber);
        presenter.getTranslationById(translationId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showWord(String word) {
        this.word.setText(word);
    }

    @Override
    public void showTranslation(String translation) {
        this.translation.setText(Html.fromHtml(translation));
    }
}

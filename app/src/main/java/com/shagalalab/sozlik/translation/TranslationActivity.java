package com.shagalalab.sozlik.translation;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
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
    private int translationId;
    private Menu menu;
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
        translationId = getIntent().getIntExtra("translationId", randomNumber);
        presenter.getTranslationById(translationId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.translate, menu);
        this.menu = menu;
        presenter.isFavorite(translationId);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.favourite:
                presenter.toggleFavorite(translationId);
                break;
            default:
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

    @Override
    public void showFavorite(Boolean favourite) {
        MenuItem item = menu.findItem(R.id.favourite);
        if (favourite) {
            item.setIcon(R.drawable.ic_bookmark_ribbon);
        } else {
            item.setIcon(R.drawable.ic_bookmark_outline);
        }
    }
}

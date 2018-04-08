package com.shagalalab.sozlik.translation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;

public class TranslationActivity extends AppCompatActivity implements TranslationView {
    public static final String TRANSLATION_ID = "translationId";

    private TranslationPresenter presenter;
    private TextView word;
    private TextView translation;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        Toolbar toolbar = findViewById(R.id.translation_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        word = findViewById(R.id.word);
        translation = findViewById(R.id.translation);

        SozlikDao sozlikDao = SozlikDatabase.getSozlikDatabase(this).sozlikDao();
        int translationId = getIntent().getIntExtra(TRANSLATION_ID, 1);

        presenter = new TranslationPresenter(this, sozlikDao);
        presenter.getTranslationById(translationId);
        presenter.setLastAccessed(translationId, System.currentTimeMillis());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.translate, menu);
        menuItem = menu.findItem(R.id.menu_favourite);
        presenter.setFavoriteStatus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_favourite:
                presenter.toggleFavorite();
                return true;
            case R.id.menu_share:
                presenter.shareTranslation();
                return true;
            default:
                return false;
        }
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
        if (favourite) {
            menuItem.setIcon(R.drawable.ic_bookmark_white_24dp);
        } else {
            menuItem.setIcon(R.drawable.ic_bookmark_border_white_24dp);
        }
    }

    @Override
    public void goToShare(String word, String translation) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, word);
        intent.putExtra(Intent.EXTRA_TEXT, translation);
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_translation)));
    }
}

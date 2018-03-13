package com.shagalalab.sozlik.translation;

import android.content.Intent;
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

public class TranslationActivity extends AppCompatActivity implements TranslationView {

    public static final String TRANSLATION_ID = "translationId";

    private TranslationPresenter presenter;
    private SozlikDao sozlikDao;
    private TextView word;
    private TextView translation;
    private int translationId;
    private MenuItem menuItem;
    private final int defaultValue = 1;

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
        translationId = getIntent().getIntExtra(TRANSLATION_ID, defaultValue);
        presenter.getTranslationById(translationId);
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
                this.finish();
                break;
            case R.id.menu_favourite:
                presenter.toggleFavorite();
                break;
            case R.id.menu_share:
                presenter.shareTranslation();
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
        if (favourite) {
            menuItem.setIcon(R.drawable.ic_bookmark_ribbon);
        } else {
            menuItem.setIcon(R.drawable.ic_bookmark_outline);
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

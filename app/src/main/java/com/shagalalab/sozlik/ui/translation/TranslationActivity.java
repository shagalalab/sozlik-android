package com.shagalalab.sozlik.ui.translation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.SozlikApp;
import com.shagalalab.sozlik.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslationActivity extends BaseActivity implements TranslationView {
    public static final String TRANSLATION_ID = "translationId";
    private MenuItem menuItem;

    @BindView(R.id.word) TextView word;
    @BindView(R.id.translation) TextView translation;

    @Inject
    TranslationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        ButterKnife.bind(this);

        ((SozlikApp) getApplication()).getComponent().inject(this);

        Toolbar toolbar = findViewById(R.id.translation_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int translationId = getIntent().getIntExtra(TRANSLATION_ID, 1);

        presenter.setView(this);
        presenter.setModelById(translationId);
        presenter.getTranslation();
        presenter.setLastAccessed(System.currentTimeMillis());
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

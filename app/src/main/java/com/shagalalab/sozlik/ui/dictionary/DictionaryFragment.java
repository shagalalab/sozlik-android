package com.shagalalab.sozlik.ui.dictionary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.SozlikApp;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;
import com.shagalalab.sozlik.ui.dictionary.autocomplete.AutoCompleteListener;
import com.shagalalab.sozlik.ui.dictionary.autocomplete.WordAutoCompleteAdapter;
import com.shagalalab.sozlik.ui.dictionary.suggestion.SuggestionListener;
import com.shagalalab.sozlik.ui.dictionary.suggestion.SuggestionResultsAdapter;
import com.shagalalab.sozlik.ui.translation.TranslationActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by QAREKEN on 3/6/2018.
 */

public class DictionaryFragment extends Fragment implements DictionaryView, SuggestionListener, AutoCompleteListener,
    OnFocusChangeListener {
    public static final String TAG = DictionaryFragment.class.getName();
    private static final int THRESHOLD_NUMBER = 3;
    private SuggestionResultsAdapter suggestionResultsAdapter;
    private WordAutoCompleteAdapter wordAutoCompleteAdapter;
    private InputMethodManager inputMethodManager;

    @Inject DictionaryPresenter dictionaryPresenter;
    @Inject SozlikDao sozlikDao;

    @BindView(R.id.search_text_edit) AutoCompleteTextView searchText;
    @BindView(R.id.text_view_result) TextView message;
    @BindView(R.id.install_keyboard) TextView keyboardText;
    @BindView(R.id.suggestion_list) RecyclerView suggestionList;

    @OnClick(R.id.install_keyboard)
    public void installKeyboard() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.qqkeyboard_address)));
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SozlikApp) getActivity().getApplication()).getComponent().inject(this);

        dictionaryPresenter.setView(this);
        suggestionResultsAdapter = new SuggestionResultsAdapter(this);
        wordAutoCompleteAdapter = new WordAutoCompleteAdapter(getContext(), sozlikDao, this);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        ButterKnife.bind(this, v);

        searchText.setOnKeyListener(onKeyListener);
        searchText.setThreshold(THRESHOLD_NUMBER);
        searchText.setAdapter(wordAutoCompleteAdapter);
        searchText.setOnFocusChangeListener(this);

        suggestionList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        suggestionList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        suggestionList.setAdapter(suggestionResultsAdapter);

        dictionaryPresenter.setKeyboardMessageVisibility();
        return v;
    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                dictionaryPresenter.search(searchText.getText().toString());
                searchText.dismissDropDown();
                hideSoftKeyboard();
                return true;
            }
            return false;
        }
    };

    @Override
    public void showResults(List<SozlikDbModel> listOfResults) {
        suggestionList.scrollToPosition(0);
        suggestionResultsAdapter.updateItems(searchText.getText().toString(), listOfResults);
    }

    @Override
    public void showTranslation(int wordId) {
        Intent intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra(TranslationActivity.TRANSLATION_ID, wordId);
        startActivity(intent);
    }

    @Override
    public void showMessage(int res) {
        String text = getString(res, String.format("<b>%s</b>", searchText.getText()));
        message.setText(Html.fromHtml(text));
    }

    @Override
    public void setMessageVisible() {
        message.setVisibility(View.VISIBLE);
    }

    @Override
    public void showKeyboardMessage() {
        keyboardText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideKeyboardMessage() {
        keyboardText.setVisibility(View.GONE);
    }

    @Override
    public void onSuggestionClicked(SozlikDbModel word) {
        showTranslation(word.getId());
    }

    @Override
    public void onAutoCompleteClicked(SozlikDbModel word) {
        showTranslation(word.getId());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.search_text_edit && !hasFocus) {
            hideSoftKeyboard();
        }
    }

    private void hideSoftKeyboard() {
        getInputMethodManager().hideSoftInputFromWindow(searchText.getWindowToken(), 0);
    }

    private InputMethodManager getInputMethodManager() {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        }
        return inputMethodManager;
    }
}

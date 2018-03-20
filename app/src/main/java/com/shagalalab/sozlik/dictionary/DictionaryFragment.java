package com.shagalalab.sozlik.dictionary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.helper.PackageHelper;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;
import com.shagalalab.sozlik.translation.TranslationActivity;

import java.util.List;

/**
 * Created by QAREKEN on 3/6/2018.
 */

public class DictionaryFragment extends Fragment implements DictionaryView, SuggestionListener {
    public static final String TAG = DictionaryFragment.class.getName();
    private static final int THRESHOLD_NUMBER = 3;

    private DictionaryPresenter dictionaryPresenter;
    private AutoCompleteTextView searchText;
    private Button searchButton;
    private TextView message;
    private TextView keyboardText;
    private RecyclerView suggestionList;
    private SuggestionResultsAdapter suggestionResultsAdapter;
    private WordAutoCompleteAdapter wordAutoCompleteAdapter;
    private PackageHelper packageHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SozlikDao sozlikDao = SozlikDatabase.getSozlikDatabase(getActivity()).sozlikDao();
        packageHelper = new PackageHelper(getContext());
        dictionaryPresenter = new DictionaryPresenter(this, sozlikDao, packageHelper);
        suggestionResultsAdapter = new SuggestionResultsAdapter(this);
        wordAutoCompleteAdapter = new WordAutoCompleteAdapter(getContext(), sozlikDao.getAllWords(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        searchText = v.findViewById(R.id.search_text_edit);
        searchText.setOnKeyListener(onKeyListener);
        searchText.setThreshold(THRESHOLD_NUMBER);
        searchText.setAdapter(wordAutoCompleteAdapter);

        searchButton = v.findViewById(R.id.search_button);
        searchButton.setOnClickListener(onClickListener);

        message = v.findViewById(R.id.text_view_result);
        keyboardText = v.findViewById(R.id.install_keyboard);
        keyboardText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickKeyboardMessage();
            }
        });

        dictionaryPresenter.ifKeyboardInstalled();

        suggestionList = v.findViewById(R.id.suggestion_list);
        suggestionList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        suggestionList.setAdapter(suggestionResultsAdapter);
        return v;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dictionaryPresenter.search(searchText.getText().toString());
        }
    };

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                searchButton.callOnClick();
                return true;
            }
            return false;
        }
    };

    @Override
    public void showResults(List<SozlikDbModel> listOfResults) {
        suggestionList.scrollToPosition(0);
        suggestionResultsAdapter.updateItems(listOfResults);
    }

    @Override
    public void showTranslation(int wordId) {
        Intent intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra(TranslationActivity.TRANSLATION_ID, wordId);
        startActivity(intent);
    }

    @Override
    public void showMessage(int res) {
        String text = getString(res, searchText.getText());
        message.setText(text);
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
    public void onClickKeyboardMessage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.qqkeyboard_address)));
        startActivity(intent);
    }

    @Override
    public void onSuggestionClicked(int wordId) {
        showTranslation(wordId);
    }
}

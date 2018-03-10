package com.shagalalab.sozlik.dictionary;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;
import com.shagalalab.sozlik.translation.TranslationActivity;

import java.util.ArrayList;

/**
 * Created by QAREKEN on 3/6/2018.
 */

public class DictionaryFragment extends Fragment implements DictionaryView {
    private DictionaryPresenter dictionaryPresenter;
    private EditText searchText;
    private Button searchButton;
    private TextView message;
    private RecyclerView list;
    private SozlikDao sozlikDao;
    private Intent intent;
    private RecyclerView.LayoutManager layoutManager;
    private SuggestionResultsAdapter suggestionResultsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sozlikDao = SozlikDatabase.getSozlikDatabase(getActivity()).sozlikDao();
        dictionaryPresenter = new DictionaryPresenter(this, sozlikDao);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        searchText = v.findViewById(R.id.search_text_edit);
        searchButton = v.findViewById(R.id.search_button);
        searchButton.setOnClickListener(onClickListener);
        searchText.setOnKeyListener(onKeyListener);
        message = v.findViewById(R.id.text_view_result);
        list = v.findViewById(R.id.list_of_words);
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
    public void showResults(ArrayList<SozlikDbModel> listOfResults) {
        suggestionResultsAdapter = new SuggestionResultsAdapter(listOfResults, this);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(suggestionResultsAdapter);
    }

    @Override
    public void showTranslation(int translation) {
        intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra("translationId", translation);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        String text = "\"" + searchText.getText() + "\"" + message;
        this.message.setText(text);
    }

    @Override
    public void showError(String message) {
        this.message.setText(message);
    }
}

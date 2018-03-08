package com.shagalalab.sozlik.dictionary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shagalalab.sozlik.R;

/**
 * Created by QAREKEN on 3/6/2018.
 */

public class DictionaryFragment extends Fragment implements DictionaryView {
    private DictionaryPresenter dictionaryPresenter;
    private EditText searchText;
    private Button searchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dictionaryPresenter = new DictionaryPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        searchText = v.findViewById(R.id.search_text_edit);
        searchButton = v.findViewById(R.id.search_button);
        searchButton.setOnClickListener(onClickListener);
        searchText.setOnKeyListener(onKeyListener);
        return v;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dictionaryPresenter.searchView(searchText.getText().toString());
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
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

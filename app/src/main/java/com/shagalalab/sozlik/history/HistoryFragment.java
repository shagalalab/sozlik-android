package com.shagalalab.sozlik.history;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.translation.TranslationActivity;

/**
 * Created by QAREKEN on 3/12/2018.
 */

public class HistoryFragment extends Fragment implements HistoryView, HistoryListener {
    private RecyclerView historyList;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryAdapter historyAdapter;
    private SozlikDao sozlikDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sozlikDao = SozlikDatabase.getSozlikDatabase(getActivity()).sozlikDao();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        historyAdapter = new HistoryAdapter(this);
        historyAdapter.updateItems(sozlikDao.getHistoryList20());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        historyList = v.findViewById(R.id.history_list);
        historyList.setAdapter(historyAdapter);
        historyList.setLayoutManager(layoutManager);
        return v;
    }

    @Override
    public void onHistoryClicked(int wordId) {
        Intent intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra(TranslationActivity.TRANSLATION_ID, wordId);
        startActivity(intent);
    }

}

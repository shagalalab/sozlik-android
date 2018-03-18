package com.shagalalab.sozlik.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDao;
import com.shagalalab.sozlik.model.SozlikDatabase;
import com.shagalalab.sozlik.model.SozlikDbModel;
import com.shagalalab.sozlik.translation.TranslationActivity;

import java.util.List;

/**
 * Created by QAREKEN on 3/12/2018.
 */

public class HistoryFragment extends Fragment implements HistoryListener {
    public static final String TAG = HistoryFragment.class.getName();

    private HistoryAdapter historyAdapter;
    private SozlikDao sozlikDao;
    private List<SozlikDbModel> list;
    private RecyclerView historyList;
    private TextView emptyHistory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sozlikDao = SozlikDatabase.getSozlikDatabase(getActivity()).sozlikDao();
        historyAdapter = new HistoryAdapter(this);
    }

    @Override
    public void onResume() {
        list = sozlikDao.getHistoryList20();
        historyAdapter.updateItems(list);
        if (list.isEmpty()) {
            historyList.setVisibility(View.GONE);
            emptyHistory.setVisibility(View.VISIBLE);
        } else {
            emptyHistory.setVisibility(View.GONE);
            historyList.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        historyList = v.findViewById(R.id.history_list);
        historyList.setAdapter(historyAdapter);
        historyList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        emptyHistory = v.findViewById(R.id.empty_history);
        return v;
    }

    @Override
    public void onHistoryItemClicked(int wordId) {
        Intent intent = new Intent(getActivity(), TranslationActivity.class);
        intent.putExtra(TranslationActivity.TRANSLATION_ID, wordId);
        startActivity(intent);
    }

}

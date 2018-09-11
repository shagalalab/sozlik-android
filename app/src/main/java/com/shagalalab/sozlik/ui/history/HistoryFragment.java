package com.shagalalab.sozlik.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.SozlikApp;
import com.shagalalab.sozlik.data.SozlikDao;
import com.shagalalab.sozlik.data.SozlikDbModel;
import com.shagalalab.sozlik.ui.translation.TranslationActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by QAREKEN on 3/12/2018.
 */

public class HistoryFragment extends Fragment implements HistoryListener {
    public static final String TAG = HistoryFragment.class.getName();

    private HistoryAdapter historyAdapter;
    private RecyclerView historyList;
    private TextView emptyHistory;

    @Inject SozlikDao sozlikDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SozlikApp) getActivity().getApplication()).getComponent().inject(this);

        historyAdapter = new HistoryAdapter(this);
    }

    @Override
    public void onResume() {
        List<SozlikDbModel> list = sozlikDao.getHistoryList20();
        if (list.isEmpty()) {
            historyList.setVisibility(View.GONE);
            emptyHistory.setVisibility(View.VISIBLE);
        } else {
            historyAdapter.updateItems(list);
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
        historyList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

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

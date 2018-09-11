package com.shagalalab.sozlik.ui.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.data.SozlikDbModel;

import java.util.List;

/**
 * Created by QAREKEN on 3/12/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private HistoryListener historyListener;
    private List<SozlikDbModel> data;

    HistoryAdapter(HistoryListener historyListener) {
        this.historyListener = historyListener;
    }

    void updateItems(List<SozlikDbModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_list, parent, false);
        return new HistoryViewHolder(view, historyListener);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        SozlikDbModel dbModel = data.get(position);
        if (dbModel != null) {
            holder.populateModel(dbModel);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}

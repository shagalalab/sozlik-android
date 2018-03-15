package com.shagalalab.sozlik.history;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by QAREKEN on 3/13/2018.
 */

class HistoryViewHolder extends RecyclerView.ViewHolder {
    private TextView historyWord;
    private TextView historyDate;
    private SozlikDbModel model;
    private HistoryListener historyListener;

    HistoryViewHolder(final View itemView, HistoryListener historyListener) {
        super(itemView);
        this.historyListener = historyListener;
        historyWord = itemView.findViewById(R.id.history_list_word);
        historyDate = itemView.findViewById(R.id.history_list_date);

        LinearLayout itemContainer = itemView.findViewById(R.id.item_history_list);
        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHistoryItemClicked();
            }
        });
    }

    void populateModel(SozlikDbModel model) {
        this.model = model;
        historyWord.setText(model.getWord());
        String dateString = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date(model.getLastAccessed()));
        historyDate.setText(dateString);
    }

    private void onHistoryItemClicked() {
        if (historyListener != null && model != null) {
            historyListener.onHistoryItemClicked(model.getId());
        }
    }
}

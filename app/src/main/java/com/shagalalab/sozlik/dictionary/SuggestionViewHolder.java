package com.shagalalab.sozlik.dictionary;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.model.SozlikDbModel;

/**
 * Created by atabek on 03/15/2018.
 */

class SuggestionViewHolder extends RecyclerView.ViewHolder {
    private TextView word;

    SuggestionViewHolder(View itemView) {
        super(itemView);
        word = itemView.findViewById(R.id.item_suggestion_word);
    }

    void populateModel(final SozlikDbModel item, final SuggestionListener suggestionListener) {
        word.setText(item.getWord());
        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suggestionListener != null) {
                    suggestionListener.onSuggestionClicked(item.getId());
                }
            }
        });
    }
}

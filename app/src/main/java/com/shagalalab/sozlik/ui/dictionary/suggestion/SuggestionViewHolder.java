package com.shagalalab.sozlik.ui.dictionary.suggestion;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shagalalab.sozlik.R;
import com.shagalalab.sozlik.data.SozlikDbModel;

/**
 * Created by atabek on 03/15/2018.
 */

class SuggestionViewHolder extends RecyclerView.ViewHolder {
    private TextView word;
    private ImageView flagFrom;
    private ImageView flagTo;

    SuggestionViewHolder(View itemView) {
        super(itemView);
        word = itemView.findViewById(R.id.item_suggestion_word);
        flagFrom = itemView.findViewById(R.id.item_suggestion_flag_from);
        flagTo = itemView.findViewById(R.id.item_suggestion_flag_to);
    }

    void populateModel(String originalWord, final SozlikDbModel item, final SuggestionListener suggestionListener) {
        word.setText(Html.fromHtml(item.getWord().replace(originalWord, String.format("<b>%s</b>", originalWord))));
        flagFrom.setImageResource(item.getFromResource());
        flagTo.setImageResource(item.getToResource());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (suggestionListener != null) {
                    suggestionListener.onSuggestionClicked(item);
                }
            }
        });
    }
}

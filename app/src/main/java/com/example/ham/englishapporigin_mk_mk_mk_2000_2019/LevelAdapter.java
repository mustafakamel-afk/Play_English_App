package com.example.ham.englishapporigin_mk_mk_mk_2000_2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by H.A.M on 7/15/2018.
 */

public class LevelAdapter extends ArrayAdapter<Level> {

    private String mBackgroundColor;

    public LevelAdapter(Context context, ArrayList<Level> levels, String mBackgroundColor) {
        super(context, 0, levels);
        this.mBackgroundColor = mBackgroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        Level currentLevel = getItem(position);

        TextView levelTextView = (TextView) listItemView.findViewById(R.id.level_text_view);

        levelTextView.setText(String.valueOf(currentLevel.getmLevelNumber()));

        RatingBar levelRatingBar = (RatingBar) listItemView.findViewById(R.id.level_rating_bar);

        levelRatingBar.setRating(currentLevel.getmRating());

        if (mBackgroundColor.equals("blue")) {
            levelTextView.setBackgroundResource(R.drawable.background_blue_1);
        } else if (mBackgroundColor.equals("orange")) {
            levelTextView.setBackgroundResource(R.drawable.background_orange);
        } else if (mBackgroundColor.equals("purple")) {
            levelTextView.setBackgroundResource(R.drawable.background_purple);
        } else {

            levelTextView.setBackgroundResource(R.drawable.background_indigo);
        }

        return listItemView;
    }
}

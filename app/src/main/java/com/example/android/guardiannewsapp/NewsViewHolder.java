package com.example.android.guardiannewsapp;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by UFO_24 on 04-12-2017.
 */

public class NewsViewHolder {

    public NewsViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    @BindView(R.id.author_id)
    TextView authorTextView;

    @BindView(R.id.date_id)
    TextView dateTextView;

    @BindView(R.id.section_id)
    TextView sectionTextView;

    @BindView(R.id.title_id)
    TextView titleTextView;

    public TextView getAuthorTextView() {
        return authorTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public TextView getSectionTextView() {
        return sectionTextView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

}

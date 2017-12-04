package com.example.android.guardiannewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by UFO_24 on 28-11-2017.
 */

public class GuardianNewsAdapter extends ArrayAdapter<GuardianNews> {

    public GuardianNewsAdapter(Context context) {
        super(context, -1, new ArrayList<GuardianNews>());
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView;
        NewsViewHolder viewHolder;

        GuardianNews guardianNewsItem = getItem(position);

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            viewHolder = new NewsViewHolder(listItemView);

            listItemView.setTag(viewHolder);
        } else {
            listItemView = convertView;
            viewHolder = (NewsViewHolder) listItemView.getTag();
        }

        populateViews(viewHolder, guardianNewsItem);

        return listItemView;

    }

    void populateViews(NewsViewHolder viewHolder, GuardianNews newsItem) {

        TextView authorTextView = viewHolder.getAuthorTextView();
        authorTextView.setText(newsItem.getNewsAuthor());

        TextView dateTextView = viewHolder.getDateTextView();
        dateTextView.setText(newsItem.getNewsDate());

        TextView sectionTextView = viewHolder.getSectionTextView();
        sectionTextView.setText(newsItem.getNewsSection());

        TextView titleTextView = viewHolder.getTitleTextView();
        titleTextView.setText(newsItem.getNewsTitle());

    }
}

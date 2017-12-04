package com.example.android.guardiannewsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by UFO_24 on 29-11-2017.
 */

public class GuardianNewsLoader extends AsyncTaskLoader<List<GuardianNews>> {

    public GuardianNewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<GuardianNews> loadInBackground() {
        List<GuardianNews> newsList = null;

        try {
            URL url = Utils.createNewsDataUrl();

            String jsonResponse = Utils.makeHttpRequest(url);

            newsList = Utils.parseJsonData(jsonResponse);
        } catch (IOException exception) {
            Log.e("Utils", "Error load in background, ", exception);
        }
        return newsList;
    }
}

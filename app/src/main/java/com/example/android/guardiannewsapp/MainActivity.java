package com.example.android.guardiannewsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<GuardianNews>> {

    private GuardianNewsAdapter newsAdapter;
    private List<GuardianNews> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ListView listView = (ListView) findViewById(R.id.list_view);

        newsAdapter = new GuardianNewsAdapter(this);

        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GuardianNews news = newsAdapter.getItem(i);
                String newsUrl = news.getNewsUrl();

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(newsUrl));

                //Before starting the implicit Intent, verify that an app exists to receive the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<GuardianNews>> onCreateLoader(int id, Bundle bundle) {
        return new GuardianNewsLoader(this);
    }


    @Override
    public void onLoadFinished(Loader<List<GuardianNews>> loader, List<GuardianNews> data) {

        if (data != null) {
            newsAdapter.clear();
            newsAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<GuardianNews>> loader) {

    }


}


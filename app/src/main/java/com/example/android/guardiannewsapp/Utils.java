package com.example.android.guardiannewsapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by UFO_24 on 26-11-2017.
 */

public class Utils {
    static String createNewsDataStringUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "Android")
                .appendQueryParameter("api-key", "test");
        String url = builder.build().toString();
        return url;
    }

    static URL createNewsDataUrl() {
        String newsDataUrl = createNewsDataStringUrl();

        try {
            return new URL(newsDataUrl);
        } catch (MalformedURLException exception) {
            Log.e("createNewsDataUrl", "Error while url creation", exception);
            return null;
        }
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            String line = bufferedReader.readLine();

            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }

        return output.toString();
    }

    static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("Utils", "error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException exception) {
            Log.e("Utils", "error httprequest", exception);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    static List<GuardianNews> parseJsonData(String jsonResponse) {

        ArrayList<GuardianNews> newsItems = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONObject jsonResponseObject = jsonObject.getJSONObject("response");

            JSONArray resultsArray = jsonResponseObject.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); ++i) {

                JSONObject arrayItem = resultsArray.getJSONObject(i);
                String newsTitle = arrayItem.getString("webTitle");
                String newsDate = arrayItem.getString("webPublicationDate");

                int index = newsDate.indexOf("T");

                String newsFormattedDate = newsDate.substring(0, index);

                String newsSection = arrayItem.getString("sectionName");
                String newsUrl = arrayItem.getString("webUrl");

                String newsAuthor = "";
                JSONArray tagsArray = arrayItem.getJSONArray("tags");

                if (tagsArray.length() == 0) {
                    newsAuthor ="";
                } else {
                    for (int j = 0; j < tagsArray.length(); ++j) {
                        JSONObject tagArrayItem = tagsArray.getJSONObject(j);
                        newsAuthor += tagArrayItem.getString("webTitle") + ". ";

                    }
                }

                newsItems.add(new GuardianNews(newsTitle, newsAuthor, newsFormattedDate, newsUrl, newsSection));
            }

        } catch (JSONException exception) {
            Log.e("Utils", "Error while parsing", exception);
        }

        return newsItems;

    }

}

/**
 * Created by UFO_24 on 27-11-2017.
 */

package com.example.android.guardiannewsapp;

public class GuardianNews {

    public GuardianNews(String title, String author, String date, String url, String section) {
        this.newsTitle = title;
        this.newsAuthor = author;
        this.newsDate = date;
        this.newsUrl = url;
        this.newsSection = section;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getNewsSection() {
        return newsSection;
    }

    private String newsTitle;
    private String newsAuthor;
    private String newsDate;
    private String newsUrl;
    private String newsSection;
}

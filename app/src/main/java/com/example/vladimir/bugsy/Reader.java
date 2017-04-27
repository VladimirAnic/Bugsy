package com.example.vladimir.bugsy;

/**
 * Created by Vladimir on 27.4.2017..
 */

public class Reader {
    public String title;
    public String link;
    public String category;
    public String description;
    public String image;
    public String pubDate;

    public Reader(String title, String link, String category, String description, String image, String pubDate) {
        this.title = title;
        this.link = link;
        this.category = category;
        this.description = description;
        this.image = image;
        this.pubDate = pubDate;
    }
}

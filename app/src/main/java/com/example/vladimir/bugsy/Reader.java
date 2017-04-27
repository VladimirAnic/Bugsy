package com.example.vladimir.bugsy;

/**
 * Created by Vladimir on 27.4.2017..
 */

public class Reader {
    public static String title;
    public String link;
    public static String category;
    public static String description;
    public String image;
    public static String pubDate;

    public Reader(String title, String link, String category, String description, String image, String pubDate) {
        this.title = title;
        this.link = link;
        this.category = category;
        this.description = description;
        this.image = image;
        this.pubDate = pubDate;
    }
}

package com.biu.leftover.model;

import android.widget.ImageView;

import java.text.DateFormat;
import java.util.Date;

public class Occasion {

    private String title;
    private String info;
    private String timeDisplay;
    private Date create_time;
    private Date update_time;
    private OccasionLocation occasionLocation;
    private int score;
    private int imageIndex;
    private ImageView imageView;

    public Occasion() {
    }

    public Occasion(String title, String info, Date create_time, OccasionLocation occasionLocation) {
        this.title = title;
        this.info = info;
        this.timeDisplay = DateFormat.getDateTimeInstance().format(create_time);
        this.create_time = create_time;
        this.occasionLocation = occasionLocation;
        this.score = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getTimeDisplay() {
        return timeDisplay;
    }

    public OccasionLocation getOccasionLocation() {
        return occasionLocation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }
}

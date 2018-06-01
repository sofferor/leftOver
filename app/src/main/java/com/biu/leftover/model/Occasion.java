package com.biu.leftover.model;

import android.widget.ImageView;

import java.text.DateFormat;
import java.util.Date;

public class Occasion {

    private String title;
    private String info;
    private String create_time_display;
    private Date create_time;
    private Date update_time;
    private String location;
    private int score;
    private ImageView imageView;

    public Occasion() {
        this.create_time_display = DateFormat.getDateTimeInstance().format(new Date());
    }

    public Occasion(String title, String info) {
        this.title = title;
        this.info = info;
        this.create_time_display = DateFormat.getDateTimeInstance().format(new Date());
    }

    public Occasion(String title, String info, ImageView imageView) {
        this.title = title;
        this.info = info;
        this.imageView = imageView;
        this.create_time_display = DateFormat.getDateTimeInstance().format(new Date());
    }

    public Occasion(String title, String info, String location) {
        this.title = title;
        this.info = info;
        this.location = location;
        this.create_time_display = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
    }

    public Occasion(String title, String info, String location, ImageView imageView) {
        this.title = title;
        this.info = info;
        this.location = location;
        this.imageView = imageView;
        this.create_time_display = DateFormat.getDateTimeInstance().format(new Date());
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getCreate_time_display() {
        return create_time_display;
    }

    public String getLocation() {
        return location;
    }

    public int getScore() {
        return score;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCreate_time_display(String create_time_display) {
        this.create_time_display = create_time_display;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(){
        score++;
    }

    public void decreaseScore(){
        score--;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

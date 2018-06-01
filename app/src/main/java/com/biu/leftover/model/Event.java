package com.biu.leftover.model;

import android.widget.ImageView;

public class Event {

    private String title;
    private String subtitle;
    private ImageView imageView;

    public Event() {
        this.imageView = null;
    }

    public Event(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageView = null;
    }

    public Event(String title, String subtitle, ImageView imageView) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

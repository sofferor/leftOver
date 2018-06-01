package com.biu.leftover.model;

import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String user_id;
    private String user_name;
    private String email;
    private String thumbnail;
    private Date create_time;
    private Date update_time;
    private List<Occasion> occasions_added;
    private List<Occasion> occasions_followed;

    public User() {
    }

    public User(String user_id, String user_name, String email, Date create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.create_time = create_time;
        this.update_time = create_time;
        occasions_added = new LinkedList<>();
        occasions_followed = new LinkedList<>();
    }

    public User(FirebaseUser firebaseUser) {
        this(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), new Date(firebaseUser.getMetadata().getCreationTimestamp()));
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public List<Occasion> getOccasions_added() {
        return occasions_added;
    }

    public void setOccasions_added(List<Occasion> occasions_added) {
        this.occasions_added = occasions_added;
    }

    public List<Occasion> getOccasions_followed() {
        return occasions_followed;
    }

    public void setOccasions_followed(List<Occasion> occasions_followed) {
        this.occasions_followed = occasions_followed;
    }
}
